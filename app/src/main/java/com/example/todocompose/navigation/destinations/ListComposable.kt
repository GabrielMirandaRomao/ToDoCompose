package com.example.todocompose.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todocompose.ui.screens.list.ListScreen
import com.example.todocompose.ui.viewmodels.SharedViewModel
import com.example.todocompose.util.Constants.LIST_ARGUMENT_KEY
import com.example.todocompose.util.Constants.LIST_SCREEN

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(
            navArgument(name = LIST_ARGUMENT_KEY) {
                type = NavType.StringType
                defaultValue = "defaultAction"
            }
        )
    ) {
        ListScreen(
            navigateToTaskScreen = navigateToTaskScreen,
            sharedViewModel = sharedViewModel
        )
    }
}