package iam5akda.fakechef_compose.data.di

import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import iam5akda.fakechef_compose.data.realtime.FirebaseRealtimeUtility
import iam5akda.fakechef_compose.data.realtime.RealtimeDatabaseUtility

@Module
@InstallIn(SingletonComponent::class)
object RealtimeDatabaseModule {

    @Reusable
    @Provides
    fun providesFirebaseDatabase(): FirebaseDatabase = FirebaseDatabase.getInstance()

    @Reusable
    @Provides
    fun providesRealtimeDatabaseUtility(
        firebaseRealtimeUtility: FirebaseRealtimeUtility
    ): RealtimeDatabaseUtility = firebaseRealtimeUtility
}