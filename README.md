    // Room
    implementation (libs.androidx.room.runtime)
    implementation (libs.androidx.room.ktx)
    //noinspection KaptUsageInsteadOfKsp ^^)
    kapt (libs.androidx.room.compiler)

    // Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)

    // Koin
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    // Icons
    implementation(libs.androidx.material.icons.extended)

    // Coil
    implementation(libs.coil.compose)

    // Navigation Compose
    implementation (libs.androidx.navigation.compose)

    // Compose Foundation
    implementation (libs.androidx.foundation)

    // Accompanist
    implementation (libs.accompanist.systemuicontroller)

    // Splash Screen
    implementation (libs.androidx.core.splashscreen)

    // Compose Material
    implementation (libs.androidx.material)

    // Toasty
    implementation(libs.toasty)

    // Logging Interceptor
    implementation (libs.logging.interceptor)
