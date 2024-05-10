package com.solid.app.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.solid.app.ApplicationTheme
import com.solid.app.navigation.MainNavigation
import com.solid.app.ui.base.EdgeToEdgeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : EdgeToEdgeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationTheme {
                MainNavigation(
                    navController = rememberNavController()
                )
            }        }
    }
}