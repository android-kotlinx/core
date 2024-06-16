package com.androidx.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.androidx.core.outlet.InternetConnectivityListener
import com.androidx.core.outlet.toEncodedBase64

internal class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
setContent {
"".toEncodedBase64()
}
    }
}

