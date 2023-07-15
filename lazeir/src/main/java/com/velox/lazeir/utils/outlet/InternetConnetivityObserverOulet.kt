package com.velox.lazeir.utils.outlet

import android.content.Context
import androidx.lifecycle.LifecycleCoroutineScope
import com.velox.lazeir.utils.internetObserver
import kotlinx.coroutines.ExperimentalCoroutinesApi


/**
 * [internetConnectivityListener] is a observer which keep observing connectivity of the network
 * and it attaches it self to the lifecycleScope, and uses ConnectivityManager in background
 *
 * Example
 *
 * import androidx.lifecycle.lifecycleScope
 *
 *
internetConnectivityListener(lifecycleScope = lifecycleScope,
context = context,
onAvailable = {},
onLosing = {},
onLost = {},
onUnAvailable = {})

 *
 * it returns  "Available, Unavailable, Losing, Lost" state on connectivity change in the above functions respectively
 * **/
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
        onUnAvailable = onUnAvailable,
        onLosing = onLosing,
        onLost = onLost,
        context = context
    )
}