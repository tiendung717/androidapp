package com.solid.module.uikit.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.solid.module.theme.themeColors
import com.solid.module.uikit.button.AppTextButton
import io.chipmango.theme.typography.UIKitTypography

@Composable
fun DialogConfirm(
    title: String,
    message: String? = null,
    onDismissRequest: () -> Unit,
    positiveButtonText: String,
    negativeButtonText: String? = null,
    onPositiveClick: () -> Unit,
    onNegativeClick: () -> Unit = {},
    containerColor: Color = themeColors().background.Normal,
    shape: Shape = RoundedCornerShape(8.dp)
) {

    Dialog(onDismissRequest = onDismissRequest) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = containerColor,
                    shape = shape
                )
                .clip(shape),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 12.dp,
                        vertical = 16.dp
                    ),
                text = title,
                style = UIKitTypography.Title3Medium18,
                color = themeColors().text.Stronger
            )

            message?.let {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    text = it,
                    style = UIKitTypography.Body1Regular16,
                    color = themeColors().text.Normal
                )
            }

            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
                    .align(Alignment.End),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (negativeButtonText != null) {
                    AppTextButton(
                        onClick = {
                            onNegativeClick()
                            onDismissRequest()
                        },
                        text = negativeButtonText,
                        textColor = themeColors().text.Normal,
                    )
                }

                AppTextButton(
                    onClick = {
                        onPositiveClick()
                        onDismissRequest()
                    },
                    text = positiveButtonText,
                    textColor = themeColors().text.Stronger,
                )
            }
        }
    }
}
