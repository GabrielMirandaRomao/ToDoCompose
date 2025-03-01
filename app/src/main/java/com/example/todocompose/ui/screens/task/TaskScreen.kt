package com.example.todocompose.ui.screens.task

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.todocompose.data.models.ToDoTask
import com.example.todocompose.util.Action

@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit
) {
    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask = selectedTask,
                navigateToListScreen = navigateToListScreen
            )
        },
        content = { innerPadding ->
            if (selectedTask != null) {
                Text(
                    modifier = Modifier.padding(innerPadding),
                    text = selectedTask.description
                )
            }
        }
    )
}