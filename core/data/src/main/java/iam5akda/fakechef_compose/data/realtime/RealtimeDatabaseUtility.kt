package iam5akda.fakechef_compose.data.realtime

import kotlinx.coroutines.flow.Flow

interface RealtimeDatabaseUtility {
    fun <T> getRealtimeValue(reference: String, type: Class<T>): Flow<T>
    fun clearRealtimeListener(reference: String)
    fun setRealtimeValue(reference: String, value: Any): Flow<Unit>
    fun removeRealtimeValue(reference: String): Flow<Unit>
}