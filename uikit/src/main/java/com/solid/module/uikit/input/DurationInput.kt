package com.solid.module.uikit.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solid.module.theme.themeColors
import com.solid.module.uikit.container.Container
import io.chipmango.theme.typography.UIKitTypography
import java.time.Duration

@Composable
fun DurationInput(
    modifier: Modifier = Modifier,
    title: String,
    duration: Duration,
    containerColor: Color = themeColors().background.Stronger,
    contentColor: Color = themeColors().text.Stronger,
    contentPadding: PaddingValues = PaddingValues(8.dp),
    shape: Shape = RoundedCornerShape(8.dp),
    textStyle: TextStyle = UIKitTypography.Body1Regular16,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            style = textStyle,
            color = contentColor,
            textAlign = TextAlign.Start
        )


    }
}

private fun formatDuration(duration: Duration): String {
    val totalSeconds = duration.seconds
    val hours = totalSeconds / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60

    return if (hours > 0) {
        String.format("%02d:%02d:%02d", hours, minutes, seconds)
    } else {
        String.format("%02d:%02d", minutes, seconds)
    }
}

@Preview
@Composable
fun DurationInputPreview() {
    DurationInput(
        title = "Duration",
        duration = Duration.ofSeconds(600),
        containerColor = Color.Gray,
        contentColor = Color.White,
        textStyle = UIKitTypography.Body1Regular16
    )
}