package com.velox.lazeir.utils.internetobserver

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.MutableLiveData
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

    @Composable
    @OptIn(ExperimentalCoroutinesApi::class)
    fun InternetConnectivityListener(
        lifecycleScope: LifecycleCoroutineScope,
        stateChangeText: MutableLiveData<String>?,
        onAvailable: () -> Unit,
        onUnAvailable: () -> Unit,
        onLosing: () -> Unit,
        onLost: () -> Unit,
    )
}