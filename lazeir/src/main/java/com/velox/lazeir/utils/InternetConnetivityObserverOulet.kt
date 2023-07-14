package com.velox.lazeir.utils

import android.content.Context
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
fun internetConnectivityListener(
    lifecycleScope: LifecycleCoroutineScope,
    onAvailable: (it: String) -> Unit,
    onUnAvailable: (it: String) -> Unit = {},
    onLosing: (it: String) -> Unit = {},
    onLost: (it: String) -> Unit,
    context: Context
) {
    return internetObserver.internetConnectivityListener(
        lifecycleScope = lifecycleScope,
        onAvailable = onAvailable,
        onUnAvailable=onUnAvailable,
        onLosing = onLosing,
        onLost = onLost,
        context=context
    )
}