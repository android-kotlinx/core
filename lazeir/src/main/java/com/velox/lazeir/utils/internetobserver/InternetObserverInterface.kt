package com.velox.lazeir.utils.internetobserver

import android.content.Context
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi

interface InternetObserverInterface {
    @OptIn(ExperimentalCoroutinesApi::class)
    fun internetConnectivityListener(
        lifecycleScope: LifecycleCoroutineScope,
        onAvailable: (it: String) -> Unit,
        onUnAvailable: (it: String) -> Unit = {},
        onLosing: (it: String) -> Unit = {},
        onLost: (it: String) -> Unit,
        context: Context,
    )
}