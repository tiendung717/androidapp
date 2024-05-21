package com.solid.module.uikit.topbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardBackspace
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.solid.module.theme.themeColors
import com.solid.module.uikit.menu.ContextMenu
import com.solid.module.uikit.menu.ContextMenuItem
import io.chipmango.theme.typography.UIKitTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    actionIcon: ImageVector? = null,
    navigationIcon: ImageVector? = Icons.AutoMirrored.Rounded.KeyboardBackspace,
    containerColor: Color = themeColors().background.Normal,
    iconTintColor: Color = themeColors().text.Stronger,
    titleColor: Color = themeColors().text.Stronger,
    onNavigationClick: () -> Unit = {},
    onActionClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = UIKitTypography.Title3SemiBold18
            )
        },
        navigationIcon = navigationIcon?.let {
            {
                IconButton(onClick = onNavigationClick) {
                    Icon(it, contentDescription = "Back")
                }
            }
        } ?: {},
        actions = actionIcon?.let {
            {
                IconButton(onClick = onActionClick) {
                    Icon(it, contentDescription = "Menu")
                }
            }
        } ?: {},
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = containerColor,
            actionIconContentColor = iconTintColor,
            titleContentColor = titleColor,
            navigationIconContentColor = iconTintColor
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBarWithMenu(
    title: String,
    actionIcon: ImageVector,
    navigationIcon: ImageVector? = Icons.AutoMirrored.Rounded.KeyboardBackspace,
    onNavigationClick: () -> Unit = {},
    contextMenuItems: List<ContextMenuItem>,
    contextMenuContainerColor: Color = themeColors().background.Normal,
    contextMenuContentColor: Color = themeColors().text.Stronger
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = UIKitTypography.Title3SemiBold18
            )
        },
        navigationIcon = navigationIcon?.let {
            {
                IconButton(onClick = onNavigationClick) {
                    Icon(it, contentDescription = "Back")
                }
            }
        } ?: {},
        actions = {
            ContextMenu(
                modifier = Modifier,
                icon = actionIcon,
                contextMenuItems = contextMenuItems,
                containerColor = contextMenuContainerColor,
                contentColor = contextMenuContentColor,
                iconTintColor = contextMenuContentColor
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = themeColors().background.Normal,
            actionIconContentColor = themeColors().text.Stronger,
            titleContentColor = themeColors().text.Stronger,
            navigationIconContentColor = themeColors().text.Stronger
        )
    )
}

@Preview
@Composable
fun AppTopBarPreview() {
    AppTopBar(
        title = "Title",
        actionIcon = Icons.Rounded.Done,
        navigationIcon = Icons.Rounded.Clear
    )
}
