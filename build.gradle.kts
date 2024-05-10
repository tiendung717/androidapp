plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.hilt) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.secrets.gradle.plugin) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.firebase.performance) apply false
}

tasks {
    val clean by registering(Delete::class) {
        delete(layout.buildDirectory)
    }
}

buildscript {
    val appId by extra { "com.solid.app" }
    val minimumSdkVersion by extra { 26 }
    val buildSdkVersion by extra { 34 }
    val targetSdkVersion by extra { 34 }
    val composeCompilerVersion by extra { "1.5.12" }
}