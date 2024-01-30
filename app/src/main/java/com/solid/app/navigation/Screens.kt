package com.solid.app.navigation

import com.solid.app.BuildConfig
import io.chipmango.navigation.destination.DestinationFactory


object Screens {
    private val factory by lazy {
        DestinationFactory(BuildConfig.appScheme, BuildConfig.appHost)
    }

    val Home = factory.create(
        path = "home",
        screenName = "Screen Home",
        screenClass = "ScreenHome"
    )
    val Settings = factory.create(
        path = "settings",
        screenName = "Screen Home",
        screenClass = "ScreenHome"
    )
}