package com.solid.module.uikit.picker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DurationPicker(
    selectedHour: MutableState<Int>,
    selectedMinute: MutableState<Int>,
    selectedSecond: MutableState<Int>
) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
        LazyColumn {
            items((0..23).toList()) { hour ->
                Text(
                    text = hour.toString().padStart(2, '0'),
                    modifier = Modifier.clickable { selectedHour.value = hour }
                )
            }
        }
        LazyColumn {
            items((0..59).toList()) { minute ->
                Text(
                    text = minute.toString().padStart(2, '0'),
                    modifier = Modifier.clickable { selectedMinute.value = minute }
                )
            }
        }
        LazyColumn {
            items((0..59).toList()) { second ->
                Text(
                    text = second.toString().padStart(2, '0'),
                    modifier = Modifier.clickable { selectedSecond.value = second }
                )
            }
        }
    }
}

@Preview
@Composable
fun DurationPickerPreview() {
    val selectedHour = remember { mutableStateOf(0) }
    val selectedMinute = remember { mutableStateOf(0) }
    val selectedSecond = remember { mutableStateOf(0) }

    DurationPicker(selectedHour, selectedMinute, selectedSecond)

    Text(text = "Selected duration: ${selectedHour.value}:${selectedMinute.value}:${selectedSecond.value}")
}