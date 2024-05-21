package com.solid.module.uikit.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solid.module.uikit.calendar.extension.getMonthsInYear
import com.solid.module.uikit.calendar.extension.weeks
import io.chipmango.theme.typography.UIKitTypography
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Year
import java.time.YearMonth

typealias OnDateClicked = (LocalDate) -> Unit

@Composable
fun CalendarMonth(
    modifier: Modifier,
    month: YearMonth,
    firstDayOfWeek: DayOfWeek = DayOfWeek.SUNDAY,
    includeOutBoundDates: Boolean = false,
    onWeekDayClicked: (LocalDate) -> Unit = {},
    monthTitleContent: (@Composable (YearMonth) -> Unit)? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    weekDayHeaderTextStyle: TextStyle = MaterialTheme.typography.bodySmall,
    weekDayHeaderColor: Color = MaterialTheme.colorScheme.onBackground,
    saturdayColor: Color = MaterialTheme.colorScheme.onBackground,
    sundayColor: Color = MaterialTheme.colorScheme.onBackground,
    weekDayHeaderBackgroundColor: Color = MaterialTheme.colorScheme.background,
    weekDayContent: @Composable() (BoxScope.(LocalDate?, Boolean, Boolean) -> Unit)
) {
    val weeks by remember(month) {
        derivedStateOf { month.weeks(firstDayOfWeek, includeOutBoundDates) }
    }

    Column(
        modifier = modifier.background(color = backgroundColor),
        horizontalAlignment = Alignment.Start
    ) {
        if (monthTitleContent != null) {
            monthTitleContent(month)
        }

        WeekDayHeader(
            firstDayOfWeek = firstDayOfWeek,
            textStyle = weekDayHeaderTextStyle,
            weekDayColor = weekDayHeaderColor,
            containerColor = weekDayHeaderBackgroundColor,
            saturdayColor = saturdayColor,
            sundayColor = sundayColor
        )

        weeks.forEach { week ->
            WeekRow(
                modifier = Modifier.fillMaxWidth(),
                weekDays = week,
                month = month,
                weekDayContent = weekDayContent,
                onWeekDayClicked = onWeekDayClicked
            )
        }
    }
}

@Composable
private fun WeekDayHeader(
    firstDayOfWeek: DayOfWeek,
    containerColor: Color = MaterialTheme.colorScheme.background,
    textStyle: TextStyle = MaterialTheme.typography.bodySmall,
    weekDayColor: Color = MaterialTheme.colorScheme.onBackground,
    saturdayColor: Color = MaterialTheme.colorScheme.onBackground,
    sundayColor: Color = MaterialTheme.colorScheme.onBackground
) {
    val daysOfWeek = DayOfWeek.values().toList()
    val shiftedDaysOfWeek =
        daysOfWeek.dropWhile { it != firstDayOfWeek } + daysOfWeek.takeWhile { it != firstDayOfWeek }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(containerColor)
            .padding(vertical = 8.dp),
    ) {
        shiftedDaysOfWeek.forEach { dayOfWeek ->
            Text(
                modifier = Modifier.weight(1f),
                text = dayOfWeek.name.take(1),
                style = textStyle,
                color = when (dayOfWeek) {
                    DayOfWeek.SATURDAY -> saturdayColor
                    DayOfWeek.SUNDAY -> sundayColor
                    else -> weekDayColor
                },
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun WeekRow(
    modifier: Modifier,
    month: YearMonth,
    weekDays: List<LocalDate?>,
    onWeekDayClicked: (LocalDate) -> Unit = {},
    weekDayContent: @Composable BoxScope.(
        LocalDate?,
        Boolean,
        Boolean
    ) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        weekDays.forEach { date ->
            WeekDay(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .clickable(
                        onClick = {
                            date?.let(onWeekDayClicked)
                        },
                        indication = null,
                        interactionSource = remember {
                            MutableInteractionSource()
                        }
                    ),
                date = date,
                month = month,
                weekDayContent = weekDayContent
            )
        }
    }
}

@Composable
private fun WeekDay(
    modifier: Modifier,
    date: LocalDate?,
    month: YearMonth,
    weekDayContent: @Composable BoxScope.(
        LocalDate?,
        Boolean,
        Boolean
    ) -> Unit
) {
    Box(modifier = modifier) {
        weekDayContent(
            date,
            date == LocalDate.now(),
            date?.month == month.month
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarMonthPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CalendarMonth(
            modifier = Modifier,
            month = YearMonth.now(),
            firstDayOfWeek = DayOfWeek.SUNDAY,
            includeOutBoundDates = true,
            weekDayContent = { date, isToday, inMonth ->
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = date?.dayOfMonth?.toString() ?: "",
                    style = UIKitTypography.Body2Medium14,
                    color = when {
                        isToday -> Color.Red
                        inMonth -> Color.Black
                        else -> Color.Gray
                    }
                )
            }
        )

        CalendarMonth(
            modifier = Modifier,
            month = YearMonth.now(),
            firstDayOfWeek = DayOfWeek.MONDAY,
            includeOutBoundDates = true,
            weekDayContent = { date, isToday, inMonth ->
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = date?.dayOfMonth?.toString() ?: "",
                    style = UIKitTypography.Body2Medium14,
                    color = when {
                        isToday -> Color.Red
                        inMonth -> Color.Black
                        else -> Color.Gray
                    }
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarYearPreview() {
    val months = remember {
        getMonthsInYear(Year.now())
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(months) { month ->
            CalendarMonth(
                modifier = Modifier,
                month = month,
                firstDayOfWeek = DayOfWeek.SUNDAY,
                includeOutBoundDates = true,
                monthTitleContent = { yearMonth ->
                    Text(
                        text = yearMonth.month.toString(),
                        style = UIKitTypography.Caption2Bold10,
                        color = Color.Black
                    )
                },
                weekDayContent = { date, isToday, inMonth ->
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = date?.dayOfMonth?.toString() ?: "",
                        style = UIKitTypography.Caption2Regular10,
                        color = when {
                            isToday -> Color.Red
                            inMonth -> Color.Black
                            else -> Color.Gray
                        }
                    )
                }
            )
        }
    }
}