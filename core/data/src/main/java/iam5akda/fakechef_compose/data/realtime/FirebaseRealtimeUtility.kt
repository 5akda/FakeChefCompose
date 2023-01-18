package iam5akda.fakechef_compose.data.realtime

import android.util.Log
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

    override fun <T> getRealtimeValue(reference: String, type: Class<T>): Flow<T> {
        return callbackFlow {
            val valueListener = object : ValueEventListener {
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

            firebaseDatabase.getReference(reference).addValueEventListener(valueListener)

            awaitClose {
                firebaseDatabase.getReference(reference).removeEventListener(valueListener)
            }
        }
    }

    override fun <T> getRealtimeListValue(reference: String, type: Class<T>): Flow<List<T>> {
        val itemList = mutableListOf<T>()
        return callbackFlow {
            val listEventListener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    itemList.clear()
                    for (postSnapshot in snapshot.children) {
                        postSnapshot.getValue(type)?.let {
                            itemList.add(it)
                            Log.d("MYTEST :: ", it.toString())
                        }
                    }
                    trySendBlocking(itemList)
                }

                override fun onCancelled(error: DatabaseError) {
                    throw FirebaseRealtimeException(error.message)
                }
            }

            firebaseDatabase.getReference(reference).addValueEventListener(listEventListener)

            awaitClose {
                firebaseDatabase.getReference(reference).removeEventListener(listEventListener)
            }
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