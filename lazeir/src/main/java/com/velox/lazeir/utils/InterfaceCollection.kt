package com.velox.lazeir.utils

import com.velox.lazeir.utils.conveter.ConverterImpl
import com.velox.lazeir.utils.conveter.ConverterInterface
import com.velox.lazeir.utils.handler.HandlerImpl
import com.velox.lazeir.utils.handler.HandlerInterface
import com.velox.lazeir.utils.internetobserver.InternetObserverImpl
import com.velox.lazeir.utils.internetobserver.InternetObserverInterface

internal val converter: ConverterInterface = ConverterImpl()
internal val handler: HandlerInterface = HandlerImpl()
internal val internetObserver: InternetObserverInterface = InternetObserverImpl()
