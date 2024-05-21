package com.solid.module.uikit.input

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solid.module.uikit.container.Container
import io.chipmango.theme.typography.UIKitTypography
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun TimeInput(
    time: LocalTime,
    containerColor: Color,
    contentColor: Color,
    contentPadding: PaddingValues = PaddingValues(8.dp),
    shape: Shape = RoundedCornerShape(8.dp),
    textStyle: TextStyle = UIKitTypography.Body1Regular16,
    timePattern: String = "hh:mm a",
    onClick: () -> Unit = {}
) {
    Text(
        text = time.format(DateTimeFormatter.ofPattern(timePattern)),
        style = textStyle,
        modifier = Modifier,
        color = contentColor
    )
}

@Preview
@Composable
fun TimeInputPreview() {
    TimeInput(
        time = LocalTime.now(),
        containerColor = Color.Gray,
        contentColor = Color.White
    )
}
