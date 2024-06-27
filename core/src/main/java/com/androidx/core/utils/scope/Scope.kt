package com.androidx.core.utils.scope

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


fun inIoScope(fu:()->Unit): Job {
    val job = CoroutineScope(Dispatchers.IO).launch {
        fu()
    }
    return job
}

fun inMainScope(fu:()->Unit): Job {
    val job = CoroutineScope(Dispatchers.Main).launch {
        fu()
    }
    return job
}

fun inDefaultScope(fu:()->Unit): Job {
    val job = CoroutineScope(Dispatchers.Default).launch {
        fu()
    }
    return job
}

fun inUnconfinedScope(fu:()->Unit): Job {
    val job = CoroutineScope(Dispatchers.Unconfined).launch {
        fu()
    }
    return job
}