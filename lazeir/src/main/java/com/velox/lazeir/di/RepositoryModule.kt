package com.velox.lazeir.di

import com.velox.lazeir.data.remote.ApiService
import com.velox.lazeir.data.repository.RepositoryImp
import com.velox.lazeir.domain.repositories.Repository
import com.velox.lazeir.domain.usecase.ResponseWithAuthResBodyUseCase
import com.velox.lazeir.domain.usecase.ResponseWithAuthUseCase
import com.velox.lazeir.domain.usecase.ResponseWithReqBodyUseCase
import com.velox.lazeir.domain.usecase.ResponseWithUrlUseCase
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

    @Provides
    @Singleton
    fun provideUseCase(repository: Repository): BaseUseCase {
        return BaseUseCase(
            responseWithAuthResBodyUseCase = ResponseWithAuthResBodyUseCase(repository),
            responseWithAuthUseCase = ResponseWithAuthUseCase(repository),
            responseWithReqBodyUseCase = ResponseWithReqBodyUseCase(repository),
            responseWithUrlUseCase = ResponseWithUrlUseCase(repository)
        )
    }
}


//crVelox