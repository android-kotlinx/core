package com.velox.lazeir.domain.usecase

import com.velox.lazeir.domain.repositories.Repository
import com.velox.lazeir.utils.handler.NetworkResource
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import javax.inject.Inject

class ResponseWithAuthUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun invoke(url: String, auth: String): Flow<NetworkResource<ResponseBody>> {
        return repository.getWithHeaderAuth(url, auth)
    }
}