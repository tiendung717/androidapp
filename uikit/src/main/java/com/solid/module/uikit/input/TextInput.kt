package com.solid.module.uikit.input

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.solid.module.theme.themeColors
import io.chipmango.theme.typography.UIKitTypography

@Composable
fun TextInput(
    modifier: Modifier,
    text: String,
    hint: String,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    color: Color = themeColors().text.Stronger,
    containerColor: Color = themeColors().background.Weaker,
    hintColor: Color = themeColors().text.Normal,
    style: TextStyle = UIKitTypography.Body1Regular16,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    suffix: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    shape: Shape = TextFieldDefaults.shape,
    textAlign: TextAlign = TextAlign.Start,
    readOnly: Boolean = false,
    onTextChanged: (String) -> Unit = {}
) {
    TextField(
        modifier = modifier,
        value = text,
        onValueChange = onTextChanged,
        placeholder = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = hint,
                style = style.copy(
                    color = hintColor,
                    textAlign = textAlign
                )
            )
        },
        textStyle = style.copy(color = color, textAlign = textAlign),
        visualTransformation = visualTransformation,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            focusedTextColor = themeColors().text.Stronger,
            unfocusedTextColor = themeColors().text.Stronger,
            cursorColor = themeColors().text.Stronger,
            focusedSuffixColor = themeColors().text.Stronger,
            unfocusedSuffixColor = themeColors().text.Stronger,
            focusedPlaceholderColor = themeColors().text.Normal,
            unfocusedPlaceholderColor = themeColors().text.Normal,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        suffix = suffix,
        prefix = prefix,
        shape = shape,
        readOnly = readOnly
    )

}