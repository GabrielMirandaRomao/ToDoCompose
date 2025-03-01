package com.example.todocompose.ui.screens.list

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.todocompose.data.models.Priority
import com.example.todocompose.data.models.ToDoTask
import com.example.todocompose.ui.theme.LARGE_PADDING
import com.example.todocompose.ui.theme.PRIORITY_INDICATOR_SIZE
import com.example.todocompose.ui.theme.TASK_ITEM_ELEVATION
import com.example.todocompose.ui.theme.Typography
import com.example.todocompose.ui.theme.taskItemBackgroundColor
import com.example.todocompose.ui.theme.taskItemTextColor
import com.example.todocompose.util.RequestState

@Composable
fun ListContent(
    modifier: Modifier,
    tasks: RequestState<List<ToDoTask>>,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    if(tasks is RequestState.Success) {
        if(tasks.data.isEmpty()) {
            EmptyContent()
        } else {
            DisplayTasks(
                modifier = modifier,
                tasks = tasks.data,
                navigateToTaskScreen = navigateToTaskScreen
            )
        }
    }
}

@Composable
fun DisplayTasks(
    modifier: Modifier,
    tasks: List<ToDoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(
            items = tasks,
            key = { task ->
                task.id
            }
        ) { task ->
            TaskItem(
                toDoTask = task,
                navigateToTaskScreen = navigateToTaskScreen
            )
        }
    }
}

@Composable
fun TaskItem(
    toDoTask: ToDoTask,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = taskItemBackgroundColor,
        shape = RectangleShape,
        shadowElevation = TASK_ITEM_ELEVATION,
        onClick = {
            navigateToTaskScreen(toDoTask.id)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(all = LARGE_PADDING)
                .fillMaxWidth()
        ) {
            Row {
                Text(
                    modifier = Modifier.weight(9f),
                    text = toDoTask.title,
                    color = taskItemTextColor,
                    style = Typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Canvas(
                        modifier = Modifier
                            .size(PRIORITY_INDICATOR_SIZE)
                    ) {
                        drawCircle(
                            color = toDoTask.priority.color
                        )
                    }
                }
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = toDoTask.description,
                color = taskItemTextColor,
                style = Typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@Composable
@Preview
fun TaskItemPreview() {
    TaskItem(
        toDoTask = ToDoTask(
            id = 0,
            title = "Title",
            description = "Random text",
            priority = Priority.HIGH
        ),
        navigateToTaskScreen = { }
    )
}