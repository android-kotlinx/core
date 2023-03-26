package com.velox.lazeir.di

import com.velox.lazeir.data.remote.ApiService
import com.velox.lazeir.data.repository.RepositoryImp
import com.velox.lazeir.domain.repositories.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        apiService: ApiService
    ): Repository {
        return RepositoryImp(
            apiService = apiService
        )
    }
}

//crVelox