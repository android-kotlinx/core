package com.iserveu.demoforsdksetup

import android.app.Application
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.core.Amplify
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DemoApplication:Application() {
    override fun onCreate() {
        super.onCreate()

        try {
//            // Initialize Amplify.
//            Amplify.addPlugin(AWSCognitoAuthPlugin())
//            // Configure Amplify.
//            Amplify.configure(applicationContext)
        } catch (exception: Exception) {
            //
        }
    }
}