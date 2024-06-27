package com.androidx.core.outlet

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.MutableLiveData
import com.androidx.core.utils.internet_observer.InInternetConnectivityListener
import com.androidx.core.utils.internet_observer.inInternetConnectivityListener


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

fun internetConnectivityListener(
    lifecycleScope: LifecycleCoroutineScope,
    onAvailable: (it: String) -> Unit,
    onUnAvailable: (it: String) -> Unit = {},
    onLosing: (it: String) -> Unit = {},
    onLost: (it: String) -> Unit,
    context: Context
) {
    return inInternetConnectivityListener(
        lifecycleScope = lifecycleScope,
        onAvailable = onAvailable,
        onUnAvailable = onUnAvailable,
        onLosing = onLosing,
        onLost = onLost,
        context = context
    )
}


/**
 * it returns a composable function  "Available, Unavailable, Losing, Lost" state on connectivity change in the above functions respectively
 * **/
@Composable
fun InternetConnectivityListener(
    lifecycleScope: LifecycleCoroutineScope,
    stateChangeText: MutableLiveData<String>? = null,
    onAvailable: () -> Unit,
    onUnAvailable: () -> Unit = {},
    onLosing: () -> Unit = {},
    onLost: () -> Unit,
) {
    return InInternetConnectivityListener(
        lifecycleScope = lifecycleScope,
        stateChangeText = stateChangeText,
        onAvailable = onAvailable,
        onUnAvailable = onUnAvailable,
        onLosing = onLosing,
        onLost = onLost
    )
}
