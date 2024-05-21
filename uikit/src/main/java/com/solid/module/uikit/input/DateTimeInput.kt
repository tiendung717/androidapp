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
import io.chipmango.theme.typography.UIKitTypography
import java.time.LocalDateTime

@Composable
fun DateTimeInput(
    modifier: Modifier = Modifier,
    title: String,
    dateTime: LocalDateTime,
    containerColor: Color = themeColors().background.Stronger,
    contentColor: Color = themeColors().text.Stronger,
    contentPadding: PaddingValues = PaddingValues(8.dp),
    shape: Shape = RoundedCornerShape(8.dp),
    textStyle: TextStyle = UIKitTypography.Body1Regular16,
    timePattern: String = "hh:mm a",
    datePattern: String = "dd MMM yyyy",
    onDateClick: () -> Unit = {},
    onTimeClick: () -> Unit = {}
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            style = textStyle,
            color = contentColor,
            textAlign = TextAlign.Start
        )
        DateInput(
            date = dateTime.toLocalDate(),
            containerColor = containerColor,
            contentColor = contentColor,
            contentPadding = contentPadding,
            shape = shape,
            textStyle = textStyle,
            datePattern = datePattern,
            onClick = onDateClick
        )
        TimeInput(
            time = dateTime.toLocalTime(),
            containerColor = containerColor,
            contentColor = contentColor,
            contentPadding = contentPadding,
            shape = shape,
            textStyle = textStyle,
            timePattern = timePattern,
            onClick = onTimeClick
        )
    }
}

@Preview
@Composable
fun DateTimeInputPreview() {
    DateTimeInput(
        dateTime = LocalDateTime.now(),
        containerColor = Color.Gray,
        contentColor = Color.White,
        title = "Date Time"
    )
}