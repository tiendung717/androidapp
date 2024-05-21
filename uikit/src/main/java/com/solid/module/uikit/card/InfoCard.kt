package com.solid.module.uikit.card

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Circle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solid.module.theme.themeColors
import com.solid.module.uikit.container.Container
import io.chipmango.theme.typography.UIKitTypography

@Composable
fun InfoCard(
    modifier: Modifier,
    title: String,
    subtitle: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    titleTextStyle: TextStyle = UIKitTypography.Caption1Regular12,
    subtitleTextStyle: TextStyle = UIKitTypography.Body1Medium16,
    titleTextColor: Color = themeColors().text.Normal,
    subtitleTextColor: Color = themeColors().text.Stronger,
    containerColor: Color = themeColors().background.Normal,
    contentPadding: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 12.dp),
    shape: Shape = RoundedCornerShape(8.dp),
    blinkingEffect: Boolean = false
) {
    Container(
        modifier = modifier,
        containerColor = containerColor,
        shape = shape,
        contentPadding = contentPadding
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (leadingIcon != null) {
                    leadingIcon()
                }

                Text(
                    text = title,
                    style = titleTextStyle,
                    color = titleTextColor,
                    maxLines = 1
                )
            }

            val animatedColor by animateColorAsState(
                targetValue = if (blinkingEffect) subtitleTextColor.copy(0.2f) else subtitleTextColor,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 1000, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "blinkingEffect"
            )

            Text(
                text = subtitle,
                style = subtitleTextStyle,
                color = animatedColor,
                maxLines = 1
            )
        }
    }
}

@Preview
@Composable
fun InfoCardPreview() {
    InfoCard(
        modifier = Modifier,
        title = "Working Time",
        subtitle = "02:00"
    )
}