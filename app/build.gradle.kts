import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    kotlin("plugin.serialization") version "2.0.21"
}

val localProperties = Properties().apply {
    val propsFile = rootProject.file("local.properties")
    if (propsFile.exists()) {
        load(propsFile.inputStream())
    } else {
        error("Arquivo local.properties não encontrado na raiz do projeto!")
    }
}

// Pega com segurança e falha já na configuração, não no cast
val mapsKey: String = localProperties.getProperty("MAPS_API_KEY")
    ?: error("MAPS_API_KEY não definida no local.properties")

android {
    namespace = "com.example.nearby"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.nearby"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        resValue("string", "google_maps_key", mapsKey)
        buildConfigField("String", "MAPS_API_KEY", "\"$mapsKey\"")



        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true

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

    implementation(libs.maps.compose)
    implementation(libs.kotlin.serialization)
    implementation(libs.navigation.compose)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    implementation(platform(libs.ktor.bom))
    implementation(libs.bundles.ktor)
    implementation(libs.zxing)
    implementation(libs.zxing.android.embedded)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}