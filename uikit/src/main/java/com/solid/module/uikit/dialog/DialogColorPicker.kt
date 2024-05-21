package com.solid.module.uikit.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.solid.module.theme.themeColors
import com.solid.module.uikit.button.AppTextButton
import io.chipmango.theme.typography.UIKitTypography


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DialogColorPicker(
    title: String,
    currentColor: Color?,
    colors: List<Color>,
    onDismissRequest: () -> Unit,
    containerColor: Color = themeColors().background.Normal,
    shape: Shape = RoundedCornerShape(8.dp),
    negativeButtonText: String? = null,
    positiveButtonText: String,
    onColorChanged: (Color) -> Unit
) {
    var currentSelectedItem by remember {
        mutableStateOf(currentColor)
    }

    Dialog(onDismissRequest = onDismissRequest) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = containerColor, shape = shape)
                .clip(shape)
                .padding(
                    horizontal = 16.dp,
                    vertical = 16.dp
                ),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = title,
                style = UIKitTypography.Body1Medium16,
                color = themeColors().text.Stronger
            )

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                colors.forEach { color ->
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(color = color, shape = RoundedCornerShape(4.dp))
                            .clickable {
                                currentSelectedItem = color
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        if (currentSelectedItem == color) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = Icons.Rounded.Check,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                }
            }

            HorizontalDivider(color = themeColors().divider.Normal)

            Row(
                modifier = Modifier.align(Alignment.End),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                negativeButtonText?.let {
                    AppTextButton(
                        onClick = onDismissRequest,
                        text = it
                    )
                }

                AppTextButton(
                    onClick = {
                        currentSelectedItem?.let { onColorChanged(it) }
                        onDismissRequest()
                    },
                    text = positiveButtonText
                )
            }
        }
    }
}