package com.solid.module.uikit.row

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solid.module.theme.themeColors
import io.chipmango.theme.typography.UIKitTypography

@Composable
fun SingleRow(
    modifier: Modifier,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = 12.dp,
        vertical = 8.dp
    ),
    title: String,
    titleTextStyle: TextStyle = UIKitTypography.Body1Regular16,
    titleTextColor: Color = themeColors().text.Stronger,
    actionIconTintColor: Color = themeColors().text.Normal,
    clickable: Boolean = false,
    showNavigationIcon: Boolean = clickable,
    containerColor: Color = Color.Transparent,
    shape: Shape = RoundedCornerShape(8.dp),
    onClick: () -> Unit = {},
    leadIcon: (@Composable () -> Unit)? = null,
    trailIcon: (@Composable () -> Unit)? = {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
            contentDescription = null,
            tint = actionIconTintColor
        )
    },
    widget: (@Composable () -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .background(
                color = containerColor,
                shape = shape
            )
            .clip(shape)
            .clickable(
                onClick = onClick,
                enabled = clickable,
                indication = null,
                interactionSource = remember {
                    MutableInteractionSource()
                }
            )
            .padding(contentPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (leadIcon != null) {
            leadIcon()
        }

        Text(
            modifier = Modifier.weight(1f),
            text = title,
            style = titleTextStyle,
            color = titleTextColor
        )

        if (widget != null) {
            widget()
        }

        if (showNavigationIcon && trailIcon != null) {
            trailIcon()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RowClickablePreview() {
    SingleRow(
        modifier = Modifier.fillMaxWidth(),
        title = "Title",

    ) {
        Text("Content")
    }
}