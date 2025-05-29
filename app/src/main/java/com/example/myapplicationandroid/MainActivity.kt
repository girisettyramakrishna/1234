plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.example.myapplicationandroid"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplicationandroid"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
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
        // Make sure JavaVersion.VERSION_17 is supported by your Gradle plugin
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        // JVM target should be set as a string
        jvmTarget = "17"
    }

    buildFeatures {
        // Enable Jetpack Compose support
        compose = true
    }

    composeOptions {
        // Make sure this matches the Kotlin compiler extension version you have
        kotlinCompilerExtensionVersion = "1.5.14"
    }

    // buildToolsVersion and ndkVersion are usually set like this
    buildToolsVersion = "35.0.0"
    ndkVersion = "27.0.12077973"
}

dependencies {
    // Compose BOM to manage Compose versions
    implementation(platform("androidx.compose:compose-bom:2024.05.00"))

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.2")

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")

    implementation("androidx.compose.material3:material3")

    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
