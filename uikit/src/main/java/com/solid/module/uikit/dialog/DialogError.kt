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
fun DialogError(
    title: String,
    message: String? = null,
    onDismissRequest: () -> Unit,
    positiveButtonText: String,
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 12.dp,
                        vertical = 12.dp
                    ),
                text = title,
                style = UIKitTypography.Title3Medium18,
                color = themeColors().negative.Normal
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
                    .padding(horizontal = 12.dp)
                    .padding(vertical = 12.dp)
                    .align(Alignment.End),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppTextButton(
                    onClick = onDismissRequest,
                    text = positiveButtonText,
                    textColor = themeColors().text.Stronger,
                )
            }
        }
    }
}
