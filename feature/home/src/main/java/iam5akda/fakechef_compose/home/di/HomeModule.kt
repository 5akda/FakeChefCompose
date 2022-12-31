package iam5akda.fakechef_compose.home.di

import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import iam5akda.fakechef_compose.home.repository.HomeAnimationSource
import iam5akda.fakechef_compose.home.repository.HomeRepository
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object HomeModule {

    @Provides
    @ViewModelScoped
    fun providesRepository(
        animationSource: HomeAnimationSource
    ): HomeRepository = animationSource
}