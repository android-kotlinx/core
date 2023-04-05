package com.velox.lazeir.di

import com.velox.lazeir.domain.usecase.ResponseWithAuthResBodyUseCase
import com.velox.lazeir.domain.usecase.ResponseWithAuthUseCase
import com.velox.lazeir.domain.usecase.ResponseWithReqBodyUseCase
import com.velox.lazeir.domain.usecase.ResponseWithUrlUseCase

data class BaseUseCase constructor(
    val responseWithAuthResBodyUseCase: ResponseWithAuthResBodyUseCase,
    val responseWithAuthUseCase: ResponseWithAuthUseCase,
    val responseWithReqBodyUseCase: ResponseWithReqBodyUseCase,
    val responseWithUrlUseCase: ResponseWithUrlUseCase

)