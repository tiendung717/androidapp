package com.solid.module.uikit.calendar.extension

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Year
import java.time.YearMonth
import java.time.temporal.TemporalAdjusters


fun YearMonth.weeks(firstDayOfWeek: DayOfWeek, includeDays: Boolean): List<List<LocalDate?>> {
    val firstDayOfMonth = getFirstDayOfMonth(firstDayOfWeek)
    val lastDayOfMonth = getLastDayOfMonth(firstDayOfWeek)

    val weeks = mutableListOf<List<LocalDate?>>()
    var currentDay = firstDayOfMonth
    while (currentDay <= lastDayOfMonth) {
        val week = (0 until 7).map { dayOffset ->
            val day = currentDay.plusDays(dayOffset.toLong())
            when (day.month) {
                month -> day
                else -> when (includeDays) {
                    true -> day
                    false -> null
                }
            }
        }
        weeks.add(week)
        currentDay = currentDay.plusDays(7)
    }
    return weeks
}

fun YearMonth.getFirstDayOfMonth(firstDayOfWeek: DayOfWeek = DayOfWeek.SUNDAY): LocalDate {
    return atDay(1).with(TemporalAdjusters.previousOrSame(firstDayOfWeek))
}

fun YearMonth.getLastDayOfMonth(firstDayOfWeek: DayOfWeek = DayOfWeek.SUNDAY): LocalDate {
    val lastDayOfWeek = when (firstDayOfWeek) {
        DayOfWeek.SUNDAY -> DayOfWeek.SATURDAY
        else -> DayOfWeek.SUNDAY
    }
    return atEndOfMonth().with(TemporalAdjusters.nextOrSame(lastDayOfWeek))
}

fun getMonthsInYear(year: Year): List<YearMonth> {
    return (1..12).map { month ->
        YearMonth.of(year.value, month)
    }
}