package com.solid.app.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.solid.app.ui.MainActivity
import com.solid.app.ui.base.EdgeToEdgeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartActivity : EdgeToEdgeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { true }

        runMainScreen()
    }

    private fun runMainScreen() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}