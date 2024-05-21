package com.solid.module.uikit.picker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.CompositeDateValidator
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.solid.module.uikit.R
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit


@Composable
fun rememberDatePicker(
    currentDate: LocalDate,
    minDate: LocalDate? = null,
    maxDate: LocalDate? = null,
    positiveButtonText: String = stringResource(R.string.ok),
    negativeButtonText: String = stringResource(R.string.cancel),
    onDismiss: () -> Unit = {},
    onCancel: () -> Unit = {},
    onDateSelected: (LocalDate) -> Unit
) = remember(currentDate, minDate, maxDate) {
    val constraints = if (minDate != null || maxDate != null) {
        val startAt = minDate?.atStartOfDay()?.toInstant(ZoneOffset.UTC)?.toEpochMilli()
        val endAt = maxDate?.atStartOfDay()?.toInstant(ZoneOffset.UTC)?.toEpochMilli()

        val validators = mutableListOf<CalendarConstraints.DateValidator>()
        startAt?.let {
            validators.add(DateValidatorPointForward.from(it))
        }
        endAt?.let {
            validators.add(DateValidatorPointBackward.before(it))
        }
        CalendarConstraints.Builder()
            .setValidator(CompositeDateValidator.allOf(validators))
            .build()
    } else null

    MaterialDatePicker.Builder.datePicker()
        .setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR)
        .setPositiveButtonText(positiveButtonText)
        .setNegativeButtonText(negativeButtonText)
        .setCalendarConstraints(constraints)
        .setSelection(currentDate.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli())
        .build()
        .apply {
            addOnDismissListener {
                onDismiss()
            }
            addOnCancelListener {
                onCancel()
            }
            addOnPositiveButtonClickListener { timestamp ->
                val dateTime = LocalDate.ofEpochDay(timestamp / 86400000)
                onDateSelected(dateTime)
            }
        }
}


@Composable
fun rememberDateRangePicker(
    minDate: LocalDate? = null,
    maxDate: LocalDate? = null,
    positiveButtonText: String = stringResource(R.string.ok),
    negativeButtonText: String = stringResource(R.string.cancel),
    onDismiss: () -> Unit = {},
    onCancel: () -> Unit = {},
    onDateSelected: (LocalDate, LocalDate) -> Unit
) = remember(minDate, maxDate) {
    val constraints = if (minDate != null || maxDate != null) {
        val startAt = minDate?.atStartOfDay()?.toInstant(ZoneOffset.UTC)?.toEpochMilli()
        val endAt = maxDate?.atStartOfDay()?.toInstant(ZoneOffset.UTC)?.toEpochMilli()

        val validators = mutableListOf<CalendarConstraints.DateValidator>()
        startAt?.let {
            validators.add(DateValidatorPointForward.from(it))
        }
        endAt?.let {
            validators.add(DateValidatorPointBackward.before(it))
        }
        CalendarConstraints.Builder()
            .setValidator(CompositeDateValidator.allOf(validators))
            .build()
    } else null

    MaterialDatePicker.Builder.dateRangePicker()
        .setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR)
        .setPositiveButtonText(positiveButtonText)
        .setNegativeButtonText(negativeButtonText)
        .setCalendarConstraints(constraints)
        .build()
        .apply {
            addOnDismissListener {
                onDismiss()
            }
            addOnCancelListener {
                onCancel()
            }
            addOnPositiveButtonClickListener { selection ->
                val startDate = LocalDate.ofEpochDay(selection.first / 86400000)
                val endDate = LocalDate.ofEpochDay(selection.second / 86400000)
                onDateSelected(startDate, endDate)
            }
        }
}

@Composable
fun rememberTimePicker(
    currentTime: LocalTime,
    positiveButtonText: String = stringResource(R.string.ok),
    negativeButtonText: String = stringResource(R.string.cancel),
    onDismiss: () -> Unit = {},
    onCancel: () -> Unit = {},
    onTimeSelected: (LocalTime) -> Unit
) = remember(currentTime) {
    MaterialTimePicker.Builder()
        .setPositiveButtonText(positiveButtonText)
        .setNegativeButtonText(negativeButtonText)
        .setHour(currentTime.hour)
        .setMinute(currentTime.minute)
        .build()
        .apply {
            addOnDismissListener {
                onDismiss()
            }
            addOnCancelListener {
                onCancel()
            }
            addOnPositiveButtonClickListener {
                val dateTime = currentTime
                    .withHour(hour)
                    .withMinute(minute)
                    .truncatedTo(ChronoUnit.MINUTES)
                onTimeSelected(dateTime)
            }
        }
}

@Composable
fun rememberDurationPicker(
    currentDuration: Duration,
    positiveButtonText: String = stringResource(R.string.ok),
    negativeButtonText: String = stringResource(R.string.cancel),
    onDismiss: () -> Unit = {},
    onCancel: () -> Unit = {},
    onDurationSelected: (Duration) -> Unit
) = remember(currentDuration) {
    MaterialTimePicker.Builder()
        .setTimeFormat(TimeFormat.CLOCK_24H)
        .setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK)
        .setPositiveButtonText(positiveButtonText)
        .setNegativeButtonText(negativeButtonText)
        .setHour(currentDuration.toHours().toInt())
        .setMinute(currentDuration.toMinutes().toInt())
        .build()
        .apply {
            addOnDismissListener {
                onDismiss()
            }
            addOnCancelListener {
                onCancel()
            }
            addOnPositiveButtonClickListener {
                val duration = Duration.ofSeconds(hour.toLong() * 3600 + minute.toLong() * 60)
                onDurationSelected(duration)
            }
        }
}
