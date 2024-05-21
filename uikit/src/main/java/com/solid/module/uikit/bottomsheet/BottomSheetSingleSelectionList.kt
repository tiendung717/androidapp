package com.solid.module.uikit.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.solid.module.theme.themeColors
import com.solid.module.uikit.button.AppButton
import com.solid.module.uikit.row.SingleRow
import io.chipmango.theme.typography.UIKitTypography

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun <T, V> BottomSheetSingleSelectionList(
    modifier: Modifier = Modifier,
    title: String,
    currentItem: V? = null,
    items: List<T>,
    onItemClicked: (T) -> Unit,
    buttonCtaText: String? = null,
    onCtaClicked: () -> Unit = { },
    titleFormatter: @Composable (T) -> String,
    iconFormatter: @Composable (T) -> ImageVector?,
    compareCondition: (T, V?) -> Boolean,
    titleTextStyle: TextStyle = UIKitTypography.Body1Regular16,
    titleColor: Color = themeColors().text.Stronger,
    itemTextColor: Color = themeColors().text.Normal,
    itemIconColor: Color = themeColors().text.Normal,
    itemContainerColor: Color = themeColors().background.Normal
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            text = title,
            style = UIKitTypography.Title3Bold18,
            color = titleColor,
            textAlign = TextAlign.Center
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(
                vertical = 16.dp,
                horizontal = 12.dp
            )
        ) {
            items(items) {
                SingleRow(
                    modifier = Modifier.fillMaxWidth(),
                    title = titleFormatter(it),
                    onClick = { onItemClicked(it) },
                    clickable = true,
                    showNavigationIcon = false,
                    leadIcon = iconFormatter(it)?.let {
                        {
                            Icon(
                                imageVector = it,
                                contentDescription = null,
                                tint = itemIconColor
                            )
                        }
                    },
                    titleTextColor = itemTextColor,
                    titleTextStyle = titleTextStyle,
                    widget = if (compareCondition(it, currentItem)) {
                        {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = Icons.Rounded.Check,
                                contentDescription = null,
                                tint = itemIconColor
                            )
                        }
                    } else {
                        {
                            Spacer(modifier = Modifier.size(24.dp))
                        }
                    },
                    containerColor = itemContainerColor,
                    contentPadding = PaddingValues(
                        vertical = 12.dp,
                        horizontal = 12.dp
                    )
                )
            }
        }

        buttonCtaText?.let {
            AppButton(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
                    .fillMaxWidth(),
                onClick = {
                    currentItem?.let { onCtaClicked() }
                },
                title = buttonCtaText
            )
        }
    }
}