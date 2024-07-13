import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.matin.youtech.crypto"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.matin.youtech.crypto"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    val signingPropertiesFile = rootProject.file("signing.properties")
    val signingProperties = Properties()

    if (signingPropertiesFile.exists()) {
        signingProperties.load(signingPropertiesFile.inputStream())
    }

    signingConfigs {
        create("release") {
            storeFile = file(signingProperties["release.storeFile"] as String)
            storePassword = signingProperties["release.storePassword"] as String
            keyAlias = signingProperties["release.keyAlias"] as String
            keyPassword = signingProperties["release.keyPassword"] as String
        }
    }
    buildTypes {
       create("staging") {
            applicationIdSuffix = CryptoBuildType.Staging.suffix
            isMinifyEnabled = false
            isDebuggable = true
        }
        release {
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("release")
            applicationIdSuffix = CryptoBuildType.Release.suffix
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation (libs.accompanist.pager)
    implementation (libs.accompanist.pager.indicators)
    implementation(libs.androidx.tools.core)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

enum class CryptoBuildType(val suffix: String? = "") {
    Staging(".staging"),
    Release
}