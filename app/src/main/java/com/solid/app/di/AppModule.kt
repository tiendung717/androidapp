package com.solid.app.di

import androidx.compose.material.lightColors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.chipmango.theme.colors.ThemeColor
import io.chipmango.theme.font.googleFontFamily
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideCoroutineScope() = CoroutineScope(Dispatchers.IO)

    @Singleton
    @Provides
    fun provideFont() = googleFontFamily("Roboto")

    @Singleton
    @Provides
    fun provideThemeColor() : ThemeColor {
        return ThemeColor(
            light = com.solid.module.theme.lightColors,
            dark = com.solid.module.theme.darkColors
        )
    }
}