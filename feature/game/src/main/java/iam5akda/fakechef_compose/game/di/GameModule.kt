package iam5akda.fakechef_compose.game.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import iam5akda.fakechef_compose.game.repository.GameRealtimeSource
import iam5akda.fakechef_compose.game.repository.GameRepository
import iam5akda.fakechef_compose.game.repository.HostRealtimeSource
import iam5akda.fakechef_compose.game.repository.HostRepository

@Module
@InstallIn(ViewModelComponent::class)
object GameModule {

    @Provides
    @ViewModelScoped
    fun providesGameRepository(
        gameRealtimeSource: GameRealtimeSource
    ): GameRepository = gameRealtimeSource

    @Provides
    @ViewModelScoped
    fun providesHostRepository(
        hostRepository: HostRealtimeSource
    ): HostRepository = hostRepository
}