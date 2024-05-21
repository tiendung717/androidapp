package com.solid.module.uikit.calendar

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.solid.module.theme.themeColors
import io.chipmango.theme.typography.UIKitTypography

object CalendarSpacingDefaults {
    @Composable
    fun calendarSpacing(
        monthSpacing: Dp = 16.dp,
        monthTitleSpacing: Dp = 16.dp
    ): CalendarSpacing {
        return CalendarSpacing(
            monthSpacing = monthSpacing,
            monthTitleSpacing = monthTitleSpacing
        )
    }

    @Composable
    fun calendarColors(
        dateColor: Color = themeColors().text.Stronger,
        monthTitleColor: Color = themeColors().text.Stronger
    ): CalendarColors {
        return CalendarColors(
            dateColor = dateColor,
            monthTitleColor = monthTitleColor
        )
    }

    @Composable
    fun calendarTextStyle(
        monthTitleTextStyle: TextStyle = UIKitTypography.Title3Bold18,
        dateTextStyle: TextStyle = UIKitTypography.Body2Medium14
    ): CalendarTextStyle {
        return CalendarTextStyle(
            monthTitleTextStyle = monthTitleTextStyle,
            dateTextStyle = dateTextStyle
        )
    }
}