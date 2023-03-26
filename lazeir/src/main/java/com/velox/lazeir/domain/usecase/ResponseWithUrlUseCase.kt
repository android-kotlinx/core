package com.velox.lazeir.domain.usecase


import com.velox.lazeir.domain.repositories.Repository
import com.velox.lazeir.utils.NetworkResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ResponseWithUrlUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun invoke(vUrl: String): Flow<NetworkResource<String>> {
        return repository.getUrl(vUrl)
    }
}