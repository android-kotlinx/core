package com.androidx.core.domain

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.MutableLiveData
import com.androidx.core.utils.internet_observer.InternetObserver
import kotlinx.coroutines.ExperimentalCoroutinesApi

interface InternetObserverInterface {

     fun inInternetConnectivityListener(
        context: Context,
        lifecycleScope: LifecycleCoroutineScope,
        onAvailable: (it: String) -> Unit,
        onUnAvailable: (it: String) -> Unit,
        onLosing: (it: String) -> Unit,
        onLost: (it: String) -> Unit,
    )

    @Composable
     fun InInternetConnectivityListener(
        lifecycleScope: LifecycleCoroutineScope,
        stateChangeText: MutableLiveData<String>?,
        onAvailable: () -> Unit,
        onUnAvailable: () -> Unit,
        onLosing: () -> Unit,
        onLost: () -> Unit,
    )
}

