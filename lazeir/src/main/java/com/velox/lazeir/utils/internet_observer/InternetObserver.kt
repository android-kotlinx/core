package com.velox.lazeir.utils.internet_observer

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalCoroutinesApi::class)
internal fun inInternetConnectivityListener(
    lifecycleScope: LifecycleCoroutineScope,
    onAvailable: (it: String) -> Unit,
    onUnAvailable: (it: String) -> Unit,
    onLosing: (it: String) -> Unit,
    onLost: (it: String) -> Unit,
    context: Context,
) {
    val connectivityObserver = NetworkConnectivityObserver(context)
    connectivityObserver.observe().onEach {
        when (it) {
            ConnectivityObserver.Status.Available -> onAvailable(it.name)
            ConnectivityObserver.Status.Unavailable -> onUnAvailable(it.name)
            ConnectivityObserver.Status.Losing -> onLosing(it.name)
            ConnectivityObserver.Status.Lost -> onLost(it.name)
        }
    }.launchIn(lifecycleScope)
}

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
internal fun inInternetConnectivityListener(
    lifecycleScope: LifecycleCoroutineScope,
    stateChangeText: MutableLiveData<String>?,
    onAvailable: () -> Unit,
    onUnAvailable: () -> Unit,
    onLosing: () -> Unit,
    onLost: () -> Unit,
) {
    val applicationContext = LocalContext.current
    val connectivityObserver = NetworkConnectivityObserver(applicationContext)
    LaunchedEffect(connectivityObserver) {
        connectivityObserver.observe().onEach {
            stateChangeText?.value = it.name
            when (it) {
                ConnectivityObserver.Status.Available -> onAvailable()
                ConnectivityObserver.Status.Unavailable -> onUnAvailable()
                ConnectivityObserver.Status.Losing -> onLosing()
                ConnectivityObserver.Status.Lost -> onLost()
            }
        }.launchIn(lifecycleScope)

    }
}


