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
import java.time.format.DateTimeFormatter

@Composable
fun DateInput(
    date: LocalDate,
    containerColor: Color,
    contentColor: Color,
    contentPadding: PaddingValues = PaddingValues(8.dp),
    shape: Shape = RoundedCornerShape(8.dp),
    textStyle: TextStyle = UIKitTypography.Body1Regular16,
    datePattern: String = "dd MMM yyyy",
    onClick: () -> Unit = {}
) {
    Text(
        text = date.format(DateTimeFormatter.ofPattern(datePattern)),
        style = textStyle,
        modifier = Modifier,
        color = contentColor
    )
}

@Preview
@Composable
fun DateInputPreview() {
    DateInput(
        date = LocalDate.now(),
        containerColor = Color.Gray,
        contentColor = Color.White
    )
}
