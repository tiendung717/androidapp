package com.solid.module.uikit.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.MoreHoriz
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solid.module.theme.themeColors
import io.chipmango.theme.typography.UIKitTypography

data class ContextMenuItem(
    val icon: ImageVector? = null,
    val title: String,
    val enabled: Boolean = true,
    val onClick: () -> Unit
)

@Composable
fun ContextMenu(
    modifier: Modifier,
    icon: ImageVector,
    iconTintColor: Color = themeColors().text.Stronger,
    containerColor: Color = themeColors().background.Strong,
    contextMenuItems: List<ContextMenuItem>,
    contentColor: Color = themeColors().text.Stronger
) {
    var isOpen by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        IconButton(onClick = { isOpen = !isOpen }) {
            Icon(
                imageVector = icon,
                contentDescription = "context_menu_icon",
                tint = iconTintColor
            )
        }

        DropdownMenu(
            modifier = Modifier
                .width(200.dp)
                .background(containerColor),
            expanded = isOpen,
            onDismissRequest = { isOpen = false }
        ) {
            contextMenuItems.forEachIndexed { index, contextMenuItem ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = contextMenuItem.title,
                            style = UIKitTypography.Body2Regular14,
                        )
                    },
                    leadingIcon = contextMenuItem.icon?.let {
                        {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = it,
                                contentDescription = null,
                            )
                        }
                    },
                    onClick = {
                        if (contextMenuItem.enabled) {
                            contextMenuItem.onClick()
                            isOpen = false
                        }
                    },
                    contentPadding = PaddingValues(
                        horizontal = 12.dp,
                        vertical = 4.dp
                    ),
                    colors = MenuDefaults.itemColors(
                        textColor = contentColor,
                        leadingIconColor = contentColor
                    ),
                    enabled = contextMenuItem.enabled
                )

                if (index < contextMenuItems.size - 1) {
                    HorizontalDivider(
                        color = themeColors().divider.Normal
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewContextMenu() {
    ContextMenu(
        modifier = Modifier,
        icon = Icons.Rounded.MoreHoriz,
        contextMenuItems = listOf(
            ContextMenuItem(
                icon = Icons.Rounded.Edit,
                title = "Edit",
                onClick = {}
            ),
            ContextMenuItem(
                icon = Icons.Rounded.Delete,
                title = "Delete",
                onClick = {}
            )
        )
    )
}