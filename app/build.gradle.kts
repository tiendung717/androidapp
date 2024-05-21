import org.jetbrains.kotlin.konan.properties.Properties
import java.io.FileInputStream

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.android.hilt)
    alias(libs.plugins.secrets.gradle.plugin)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.firebase.performance)
    alias(libs.plugins.google.services)
    alias(libs.plugins.ksp)
}

val versions = rootProject.file("version.properties")
val props = Properties()
props.load(FileInputStream(versions))
val major = props["majorVersion"].toString().toInt()
val minor = props["minorVersion"].toString().toInt()
val patch = props["patchVersion"].toString().toInt()
val build = props["buildNumber"].toString().toInt()

val minimumSdkVersion: Int by rootProject.extra
val buildSdkVersion: Int by rootProject.extra
val targetSdkVersion: Int by rootProject.extra
val composeCompilerVersion: String by rootProject.extra
val appId: String by rootProject.extra

android {
    namespace = "com.solid.app"
    compileSdk = buildSdkVersion

    defaultConfig {
        applicationId = appId
        minSdk = minimumSdkVersion
        targetSdk = targetSdkVersion
        versionCode = 10000 * major + 1000 * minor + 10 * patch + build
        versionName = "$major.$minor.$patch ($build)"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            storeFile = file("release.jks")
            storePassword = "123456a@"
            keyAlias = "solid"
            keyPassword = "123456a@"
        }
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
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
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = composeCompilerVersion
    }

    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
    }
}

secrets {
    propertiesFileName = "application.properties"
}

dependencies {
    implementation(project(":themes"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.material)
    implementation(libs.androidx.splasscreen)
    implementation(libs.androidx.inappreview)
    implementation(libs.androidx.workmanager)
    implementation(libs.androidx.datastore.preferences)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material3.android)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.activity)
    implementation(libs.androidx.compose.constraint.layout)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.google.fonts)
    implementation(libs.androidx.compose.lifecycle.runtime)
    implementation(libs.androidx.compose.lifecycle.viewmodel)
    implementation(libs.androidx.compose.navigation)
    debugImplementation(libs.androidx.compose.ui.tooling)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.appcheck)
    implementation(libs.firebase.appcheck.debug)
    implementation(libs.firebase.performance)

    implementation(libs.log.timber)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.work)
    implementation(libs.hilt.navigation.compose)

    implementation(libs.gson)

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    implementation(libs.chipmango.core)
    implementation(libs.chipmango.room.converter)
    implementation(libs.chipmango.revenue.cat)
    implementation(libs.revenue.cat)

//    implementation(libs.chipmango.admob)
//    implementation(libs.admob)
//    implementation(libs.ump)
}
