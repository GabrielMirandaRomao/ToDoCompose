package com.example.todocompose.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todocompose.data.models.Priority
import com.example.todocompose.ui.theme.PRIORITY_DROP_DOWN_HEIGHT

@Composable
fun PriorityDropDown(
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(PRIORITY_DROP_DOWN_HEIGHT)
            .clickable {
                expanded = true
            }
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface
            )
    ) {

    }
}