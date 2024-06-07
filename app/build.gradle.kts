plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
//    id("kotlin-kapt")
//    id("dagger.hilt.android.plugin")
//    id("com.google.firebase.crashlytics")
//    id("com.google.gms.google-services")
}

android {
    namespace = "com.iserveu.demoforsdksetup"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.iserveu.demoforsdksetup"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            pickFirst("META-INF/DEPENDENCIES")
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
}

dependencies {
//    implementation("com.amplifyframework.ui:authenticator:1.0.0")
//    implementation("com.amplifyframework:aws-auth-cognito:2.11.1")
//    implementation("com.gitlab.common-admin-sdui-sdk:aws-sdk:prod-0.0.4")

//    implementation(project(":lazeir"))

//    implementation(libs.kotlinx.core)


////////////////////////////////////////////////////////////////
//    implementation 'androidx.appcompat:appcompat:1.6.1'
//    implementation 'androidx.constraintlayout:constraintlayout'//:2.1.4'

//    implementation ("androidx.core:core-ktx:1.13.0")
//    implementation ("com.google.android.material:material:1.9.0")
//
//    implementation ("androidx.lifecycle:lifecycle-runtime-ktx")//:2.6.2'//:2.7.0'
//
//    implementation ("androidx.compose.ui:ui:1.4.3")
//
//    implementation ("androidx.compose.material:material:1.4.3")
//
//    // Retrofit
//    implementation("com.squareup.retrofit2:retrofit:2.9.0")
//    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
//    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.7")
//    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.7")
//    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
//
//    implementation("org.conscrypt:conscrypt-android:2.5.2")
//
//    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
//    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")
//
//    // Gson
//    implementation("com.google.code.gson:gson:2.10")
//
//    // Moshi
//    implementation("com.squareup.moshi:moshi:1.14.0")
//    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
//    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.13.0")
//
//    implementation("com.google.android.gms:play-services-location:21.1.0")
//
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3")
//
//    // Ktor
//    implementation("io.ktor:ktor-client-core:2.2.4")
//    implementation("io.ktor:ktor-client-serialization:2.2.4")
//    implementation("io.ktor:ktor-client-auth:2.2.4")
//    implementation("io.ktor:ktor-client-content-negotiation:2.2.4")
//    implementation("io.ktor:ktor-client-android:2.2.4")
//    implementation("io.ktor:ktor-client-logging-jvm:2.2.4")
//    implementation("io.ktor:ktor-serialization-gson:2.2.4")
//    implementation("io.ktor:ktor-serialization-kotlinx-json:2.2.4")
//    implementation("io.ktor:ktor-client-cio:2.2.4")
//    implementation("io.ktor:ktor-client-okhttp:2.2.4")
//
//    implementation("androidx.work:work-runtime:2.9.0")


    /////



////    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.appcompat)
////    implementation(libs.material)
//    implementation(libs.androidx.activity)
//    implementation(libs.androidx.constraintlayout)
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
    // Dagger Hilt
//    implementation(libs.hilt.android)
//    kapt(libs.hilt.compiler)
//    kapt(libs.hilt.android.compiler)
//    implementation(libs.hilt.navigation.compose)
////
//    implementation(platform(libs.androidx.compose.bom))
//    implementation(libs.androidx.ui)
//    implementation(libs.androidx.ui.graphics)
//    implementation(libs.androidx.ui.tooling.preview)
//    implementation(libs.androidx.material3)
//    debugImplementation(libs.androidx.ui.test.manifest)
//    debugImplementation(libs.androidx.ui.tooling)
//    androidTestImplementation(libs.androidx.ui.test.junit4)
//    implementation(libs.androidx.lifecycle.runtime.ktx)
//
//    implementation(libs.androidx.activity.compose)
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
//
//    implementation(libs.navigation.compose)
//    implementation(libs.accompanist.permissions)
//
//
//
////firebase & gsm
//    implementation(libs.firebase.firestore)
//    implementation(libs.firebase.database)
//    implementation(libs.firebase.storage)
//    implementation(libs.gsm.play.services.loaction)
////    implementation(libs.firebase.crashlytics)
//    implementation(libs.firebase.analytics)
//    implementation(libs.firebase.core)
//    implementation(platform(libs.firebase.bom))
//    implementation(libs.firebase.crashlytics.ktx)
//    implementation("com.google.firebase:firebase-crashlytics-ktx:19.0.0")
//    implementation("com.google.code.gson:gson:2.10")
//
//////in app distribution
//    val ktor_version = "2.2.4"
//
//    implementation(libs.ktor.core)
//    implementation(libs.ktor.client.content.negotiation)
//    implementation(libs.ktor.serialization.gson)
//    implementation(libs.ktor.client.apache)
//    implementation(libs.ktor.client.logging)
//
//    implementation(libs.ktor.serialization.kotlinx.json)
//    implementation(libs.ktor.server.content.negotiation)
//    implementation("io.ktor:ktor-client-serialization:$ktor_version")
//    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
//
//
//    implementation(libs.ktor.core)
//    implementation(libs.ktor.client.okhttp)
//    implementation(libs.ktor.client.serialization)
//    implementation(libs.ktor.client.logging)
//    implementation(libs.ktor.client.auth)
//    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")






    /*

    // JUnit
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Lifecycle
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")

    // Compose
        implementation(platform("androidx.compose:compose-bom:2023.06.01"))
        implementation("androidx.activity:activity-compose:1.7.2")
        implementation("androidx.compose.ui:ui")
        implementation("androidx.compose.ui:ui-graphics")
        implementation("androidx.compose.ui:ui-tooling")
        implementation("androidx.compose.ui:ui-tooling-preview")
        implementation("androidx.compose.material3:material3")
        androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.6.7")
        debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Navigation
        implementation("androidx.navigation:navigation-compose:2.6.0")

    // Hilt
        implementation("com.google.dagger:hilt-android:2.45")
        kapt("com.google.dagger:hilt-android-compiler:2.45")
        kapt("androidx.hilt:hilt-compiler:1.2.0")
        implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // Retrofit
        implementation("com.squareup.retrofit2:retrofit:2.11.0")
        implementation("com.squareup.retrofit2:converter-gson:2.9.0")
        implementation("com.squareup.retrofit2:converter-scalars:2.9.0")


        implementation("com.amplifyframework.ui:authenticator:1.0.0")
        implementation("com.amplifyframework:aws-auth-cognito:2.11.1")

        implementation ("com.gitlab.common-admin-sdui-sdk:aws-sdk:prod-0.0.7")



    */



}