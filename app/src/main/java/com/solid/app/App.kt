package com.solid.app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageInstaller
import android.os.Build
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.google.android.gms.common.GooglePlayServicesUtilLight
import com.google.firebase.FirebaseApp
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.HiltAndroidApp
import io.chipmango.revenuecat.RevenueCat
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class App : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var revenueCat: RevenueCat

    override fun onCreate() {
        super.onCreate()
        setupLog()
        setupAppCheck()
        setupFirebaseCrashlytics()
        setupRevenueCat()
    }

    private fun setupLog() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }

    private fun setupFirebaseCrashlytics() {
        Firebase.crashlytics.setCustomKey(
            "Install Source",
            installFromPlayStore()
        )
        if (BuildConfig.DEBUG){
            Firebase.crashlytics.setCrashlyticsCollectionEnabled(false);
        }
    }

    private fun setupAppCheck() {
        FirebaseApp.initializeApp(this)
        val firebaseAppCheck = FirebaseAppCheck.getInstance()
        firebaseAppCheck.installAppCheckProviderFactory(
            // Use App Check with the debug provider when running in the debug environment.
            // https://firebase.google.com/docs/app-check/android/debug-provider
            if (BuildConfig.DEBUG) {
                DebugAppCheckProviderFactory.getInstance()
            } else {
                PlayIntegrityAppCheckProviderFactory.getInstance()
            }
        )
    }

    private fun installFromPlayStore(): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val info = packageManager.getInstallSourceInfo(packageName)
            return when (info.packageSource) {
                PackageInstaller.PACKAGE_SOURCE_UNSPECIFIED -> "PACKAGE_SOURCE_UNSPECIFIED"
                PackageInstaller.PACKAGE_SOURCE_OTHER -> "PACKAGE_SOURCE_OTHER"
                PackageInstaller.PACKAGE_SOURCE_STORE -> "PACKAGE_SOURCE_STORE"
                PackageInstaller.PACKAGE_SOURCE_LOCAL_FILE -> "PACKAGE_SOURCE_LOCAL_FILE"
                PackageInstaller.PACKAGE_SOURCE_DOWNLOADED_FILE -> "PACKAGE_SOURCE_DOWNLOADED_FILE"
                else -> "Unknown"
            }
        }

        val installer = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            packageManager.getInstallSourceInfo(packageName).installingPackageName
        } else {
            packageManager.getInstallerPackageName(packageName)
        }

        val validInstaller = listOf(GooglePlayServicesUtilLight.GOOGLE_PLAY_STORE_PACKAGE)

        return if (installer != null && validInstaller.contains(installer)) {
            "PACKAGE_SOURCE_STORE"
        } else {
            "The installer = $installer"
        }
    }

    private fun setupRevenueCat() {
        revenueCat.init(this, BuildConfig.RevenueCatSdkKey, BuildConfig.DEBUG)
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder().setWorkerFactory(workerFactory).build()
}


internal class CrashReportingTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
            return
        }
        if (priority == Log.ERROR || priority == Log.WARN) {
            val crashlytics = Firebase.crashlytics
            if (t != null) {
                crashlytics.recordException(t)
            } else {
                val category = when (priority) {
                    Log.ERROR -> "E"
                    Log.WARN -> "W"
                    else -> throw IllegalStateException()
                }
                // https://firebase.google.com/docs/crashlytics/upgrade-sdk?platform=android
                // To log a message to a crash report, use the following syntax:
                // crashlytics.log("E/TAG: my message")
                crashlytics.log("$category/$tag: $message")
            }
        }
    }
}