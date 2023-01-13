package iam5akda.fakechef_compose.game.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import iam5akda.fakechef_compose.game.repository.GameRealtimeSource
import iam5akda.fakechef_compose.game.repository.GameRepository

@Module
@InstallIn(ViewModelComponent::class)
object GameModule {

    @Provides
    @ViewModelScoped
    fun providesRepository(
        gameRealtimeSource: GameRealtimeSource
    ): GameRepository = gameRealtimeSource
}