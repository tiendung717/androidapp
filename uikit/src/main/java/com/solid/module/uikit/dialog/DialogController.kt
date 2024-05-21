package com.solid.module.uikit.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class DialogController {
    private var dialogShow by mutableStateOf(false)

    fun showDialog() {
        dialogShow = true
    }

    fun dismissDialog() {
        dialogShow = false
    }

    fun isDialogShowing(): Boolean {
        return dialogShow
    }

    internal fun setInitialState(state: Boolean) {
        this.dialogShow = state
    }

    fun toggleDialog() {
        dialogShow = !dialogShow
    }

    fun isDialogDismissed(): Boolean {
        return !dialogShow
    }
}

@Composable
fun rememberDialogController(isShowing: Boolean = false) = remember {
    val controller = DialogController()
    controller.setInitialState(isShowing)
    controller
}