plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.devtools.ksp) // Use only the alias
    id("com.google.dagger.hilt.android") // Hilt doesn't have an alias in your toml yet
}

android {
    namespace = "com.example.kasthakala"
    compileSdk = 36// SDK 36 is in Preview; 35 is the stable target for 2025/2026

    defaultConfig {
        applicationId = "com.example.kasthakala"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    // Core & Compose
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation("androidx.compose.material:material-icons-extended")
    implementation("io.coil-kt:coil-compose:2.6.0")

    // Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose) // Added from libs

    // UI Components (Material 3 Support for XML Themes)
    implementation(libs.material)

    // Hilt (Dependency Injection)
    implementation("com.google.dagger:hilt-android:2.51.1")
    ksp("com.google.dagger:hilt-compiler:2.51.1")

    // Room (Database)
    val roomVersion = "2.6.1"
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")

    // Tooling
    debugImplementation(libs.androidx.ui.tooling)
}