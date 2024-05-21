package com.solid.module.uikit.empty

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solid.module.theme.themeColors
import com.solid.module.uikit.button.AppButton
import io.chipmango.theme.typography.UIKitTypography

@Composable
fun ContentEmpty(
    modifier: Modifier = Modifier,
    image: (@Composable () -> Unit)? = null,
    title: String,
    description: String? = null,
    actionText: String? = null,
    titleTextStyle: TextStyle = UIKitTypography.Body1Medium16,
    descriptionTextStyle: TextStyle = UIKitTypography.Body2Regular14,
    titleTextColor: Color = themeColors().text.Stronger,
    descriptionTextColor: Color = themeColors().text.Normal,
    onActionClick: () -> Unit = {}
) {
    // Empty content
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (image != null) {
            image()
        }

        // Title
        Text(
            text = title,
            style = titleTextStyle,
            modifier = Modifier,
            color = titleTextColor
        )

        // Description
        if (description != null) {
            Text(
                text = description,
                style = descriptionTextStyle,
                modifier = Modifier,
                color = descriptionTextColor
            )
        }

        // Action
        if (actionText != null) {
            AppButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onActionClick,
                title = actionText
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContentEmptyPreview() {
    ContentEmpty(
        modifier = Modifier.fillMaxSize(),
        title = "No data available",
        description = "There is no data available for this section.",
        actionText = "Retry"
    )
}