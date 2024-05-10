package com.solid.app

import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.solid.module.theme.themeColors
import io.chipmango.theme.theme.AppTheme

@Composable
fun ApplicationTheme(content: @Composable () -> Unit) {
    AppTheme(
        useDarkTheme = isSystemInDarkTheme()
    ) {
        Surface(color = themeColors().background.Stronger) {
            content()
        }
    }
}

fun Context.findActivity(): AppCompatActivity {
    var context = this
    while (context !is AppCompatActivity) {
        context = (context as ContextWrapper).baseContext
    }
    return context
}