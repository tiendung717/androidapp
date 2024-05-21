package com.solid.module.uikit.input

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.solid.module.theme.themeColors
import io.chipmango.theme.typography.UIKitTypography

@Composable
fun BasicTextInput(
    modifier: Modifier,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = 12.dp,
        vertical = 8.dp
    ),
    text: String,
    hint: String,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    color: Color = themeColors().text.Stronger,
    hintColor: Color = themeColors().text.Normal,
    style: TextStyle = UIKitTypography.Body1Regular16,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onTextChanged: (String) -> Unit = {}
) {
    BasicTextField(
        modifier = modifier.padding(contentPadding),
        value = text,
        onValueChange = onTextChanged,
        decorationBox = { innerTextField ->
            if (text.isEmpty()) {
                Text(
                    text = hint,
                    style = style.copy(
                        color = hintColor
                    )
                )
            }
            innerTextField()
        },
        cursorBrush = SolidColor(color),
        textStyle = style.copy(color = color),
        visualTransformation = visualTransformation,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )

}