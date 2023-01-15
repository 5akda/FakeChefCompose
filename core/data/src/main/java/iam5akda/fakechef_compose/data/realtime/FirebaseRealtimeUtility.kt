package iam5akda.fakechef_compose.data.realtime

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import iam5akda.fakechef_compose.data.realtime.exception.FirebaseRealtimeException
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirebaseRealtimeUtility @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase
) : RealtimeDatabaseUtility {

    private var valueListener: ValueEventListener? = null

    override fun <T> getRealtimeValue(reference: String, type: Class<T>): Flow<T> {
        return callbackFlow {
            valueListener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val data = snapshot.getValue(type)
                    data?.let {
                        trySendBlocking(it)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    throw FirebaseRealtimeException(error.message)
                }
            }

            valueListener?.let {
                firebaseDatabase.getReference(reference).addValueEventListener(it)
            }

            awaitClose {
                clearRealtimeListener(reference)
            }
        }
    }

    override fun clearRealtimeListener(reference: String) {
        valueListener?.let {
            firebaseDatabase.getReference(reference).removeEventListener(it)
        }
    }

    override fun setRealtimeValue(reference: String, value: Any): Flow<Unit> {
        return flow {
            firebaseDatabase.getReference(reference).setValue(value)
            emit(Unit)
        }
    }

    override fun removeRealtimeValue(reference: String): Flow<Unit> {
        return flow {
            firebaseDatabase.getReference(reference).removeValue()
            emit(Unit)
        }
    }
}