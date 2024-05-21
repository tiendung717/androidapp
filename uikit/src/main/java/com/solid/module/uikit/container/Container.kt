package com.solid.module.uikit.container


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solid.module.theme.themeColors
import com.solid.module.uikit.shadow.dropShadow

@Composable
fun Container(
    modifier: Modifier,
    shape: Shape = RoundedCornerShape(8.dp),
    contentPadding: PaddingValues = PaddingValues(16.dp),
    containerColor: Color = themeColors().background.Normal,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .dropShadow()
            .background(
                color = containerColor,
                shape = shape
            )
            .clip(shape)
            .padding(contentPadding),
        content = content
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun ContainerPreview() {
    Surface(
        color = Color(0xFFF4F4F4),
    ) {
        Container(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            shape = RoundedCornerShape(8.dp),
            containerColor = MaterialTheme.colorScheme.background
        ) {
            // Content
            Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
        }
    }

}