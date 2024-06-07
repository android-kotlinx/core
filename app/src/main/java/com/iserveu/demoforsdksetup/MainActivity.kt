package com.iserveu.demoforsdksetup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.isu.awssdk.core.AwsSdkConstants
import com.isu.awssdk.core.StringConstants
import com.isu.awssdk.core.StringConstants.IMAGE_TYPE_KEY
import com.isu.awssdk.presentation.AwsSdkActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}