package com.androidx.core.domain

import android.content.Context

interface ImplicitIntentInterface {
    fun <T> inStartImplicitActivity(
        context: Context,
        packageName: String,
        activityName: String,
        dataToIntent: List<Pair<String, T?>?>?
    )
}