package com.velox.lazeir.data.repository

import com.velox.lazeir.data.remote.ApiService
import com.velox.lazeir.domain.repositories.Repository
import com.velox.lazeir.utils.NetworkResource
import com.velox.lazeir.utils.handleNetworkResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody
import okhttp3.ResponseBody
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RepositoryImp @Inject constructor(
    private val apiService: ApiService,
) : Repository {
    override suspend fun getUrl(vUrl: String): Flow<NetworkResource<ResponseBody>> {
        /* return flow {
             emit(NetworkResource.Loading(isLoading = true))
             try {
                 val response = apiService.getUrl(vUrl)
                 if (response.body() != null && response.isSuccessful) {
                     val data = Base64.decode(response.body().toString(), Base64.DEFAULT)
                     val base64Decoded = String(data, StandardCharsets.UTF_8)
                     emit(NetworkResource.Success(base64Decoded))
                 } else emit(NetworkResource.Error("Response Body is NULL"))
             } catch (e: IOException) {
                 e.message?.let { emit(NetworkResource.Error(it)) }
             } catch (e: HttpException) {
                 e.message?.let { emit(NetworkResource.Error(it)) }
             } catch (e: IllegalStateException) {
                 e.message?.let { emit(NetworkResource.Error(it)) }
             }
             emit(NetworkResource.Loading(isLoading = false))
         }*/
        return apiService.getUrl(vUrl).handleNetworkResponse()
    }

    override suspend fun getWithHeaderAuth(
        url: String,
        header: String
    ): Flow<NetworkResource<ResponseBody>> {
        return apiService.getWithHeaderAuthorization(url, header).handleNetworkResponse()
    }

    override suspend fun postWithReqBody(
        url: String,
        body: RequestBody
    ): Flow<NetworkResource<ResponseBody>> {
        return apiService.postWithRequestBody(url, body).handleNetworkResponse()
    }

    override suspend fun postWithHeaderAuthReqBody(
        url: String,
        header: String,
        body: RequestBody
    ): Flow<NetworkResource<ResponseBody>> {
        return apiService.postWithHeaderAuthReqBody(url, header, body).handleNetworkResponse()
    }
}

