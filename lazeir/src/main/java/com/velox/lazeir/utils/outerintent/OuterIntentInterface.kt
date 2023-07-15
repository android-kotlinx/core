package com.velox.lazeir.utils.outerintent

import android.content.Context

interface OuterIntentInterface {
    fun startActivity(context: Context, packageName:String, activityName:String)
}