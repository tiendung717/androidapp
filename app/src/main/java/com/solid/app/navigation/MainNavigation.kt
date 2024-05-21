package com.solid.app.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.solid.app.ui.home.ScreenHome
import com.solid.app.ui.setting.ScreenSettings
import io.chipmango.navigation.destination.destination
import io.chipmango.navigation.navigator.Navigation


@Composable
fun MainNavigation(navController: NavHostController) {
    Navigation(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startingDestination = Screens.Home,
        enterTransition = {
            fadeIn(tween(300, easing = LinearEasing))
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                targetOffset = { it },
                animationSpec = tween(300, easing = LinearEasing)
            ) + fadeOut(tween(300, easing = LinearEasing))
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                initialOffset = { it },
                animationSpec = tween(300, easing = LinearEasing)
            ) + fadeIn(tween(300, easing = LinearEasing))
        },
        popExitTransition = {
            fadeOut(tween(300, easing = LinearEasing))
        }
    )  {
        destination(Screens.Home) {
            ScreenHome()
        }

        destination(Screens.Settings) {
            ScreenSettings()
        }
    }
}