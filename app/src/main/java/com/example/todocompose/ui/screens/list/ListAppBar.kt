package com.example.todocompose.ui.screens.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.todocompose.R
import com.example.todocompose.components.PriorityItem
import com.example.todocompose.data.models.Priority
import com.example.todocompose.ui.theme.LARGE_PADDING
import com.example.todocompose.ui.theme.TOP_APP_BAR_ELEVATION
import com.example.todocompose.ui.theme.TOP_APP_BAR_HEIGHT
import com.example.todocompose.ui.theme.Typography
import com.example.todocompose.ui.theme.topAppBarBackgroundColor
import com.example.todocompose.ui.theme.topAppBarContentColor
import com.example.todocompose.ui.viewmodels.SharedViewModel
import com.example.todocompose.util.SearchAppBarState
import com.example.todocompose.util.TrailingIconState

@Composable
fun ListAppBar(
    sharedViewModel: SharedViewModel,
    searchAppBarState: SearchAppBarState,
    searchTextState: String
) {

    when (searchAppBarState) {
        SearchAppBarState.CLOSED -> {
            DefaultListAppBar(
                onSearchClicked = {
                    sharedViewModel.searchAppBarState.value =
                        SearchAppBarState.OPENED
                },
                onSortAction = { },
                onDeleteClicked = { }
            )
        }

        else -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = { newText ->
                    sharedViewModel.searchTextState.value = newText
                },
                onCloseClicked = {
                    sharedViewModel.searchAppBarState.value =
                        SearchAppBarState.CLOSED
                    sharedViewModel.searchTextState.value = ""
                },
                onSearchClicked = {

                }
            )
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultListAppBar(
    onSearchClicked: () -> Unit,
    onSortAction: (Priority) -> Unit,
    onDeleteClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.screen_title),
                color = topAppBarContentColor
            )
        },
        actions = {
            ListAppBarActions(
                onSearchClicked = onSearchClicked,
                onSortAction = onSortAction,
                onDeleteClicked = onDeleteClicked
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = topAppBarBackgroundColor
        )
    )
}

@Composable
fun ListAppBarActions(
    onSearchClicked: () -> Unit,
    onSortAction: (Priority) -> Unit,
    onDeleteClicked: () -> Unit
) {
    SearchAction(onSearchClicked = onSearchClicked)
    SortAction(onSortAction = onSortAction)
    DeleteAllAction(onDeleteClicked = onDeleteClicked)
}

@Composable
fun SearchAction(
    onSearchClicked: () -> Unit
) {
    IconButton(
        onClick = { onSearchClicked() }
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(id = R.string.search_tasks),
            tint = topAppBarContentColor
        )
    }
}

@Composable
fun SortAction(
    onSortAction: (Priority) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = {
            expanded = true
        }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_filter_list),
            contentDescription = stringResource(id = R.string.sort_tasks),
            tint = topAppBarContentColor
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { PriorityItem(priority = Priority.LOW) },
                onClick = {
                    expanded = false
                    onSortAction(Priority.LOW)
                }
            )

            DropdownMenuItem(
                text = { PriorityItem(priority = Priority.MEDIUM) },
                onClick = {
                    expanded = false
                    onSortAction(Priority.MEDIUM)
                }
            )

            DropdownMenuItem(
                text = { PriorityItem(priority = Priority.HIGH) },
                onClick = {
                    expanded = false
                    onSortAction(Priority.HIGH)
                }
            )

            DropdownMenuItem(
                text = { PriorityItem(priority = Priority.NONE) },
                onClick = {
                    expanded = false
                    onSortAction(Priority.NONE)
                }
            )
        }
    }
}

@Composable
fun DeleteAllAction(
    onDeleteClicked: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = {
            expanded = true
        }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_vertical_menu),
            contentDescription = stringResource(id = R.string.delete_all_action),
            tint = topAppBarContentColor
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        modifier = Modifier.padding(start = LARGE_PADDING),
                        text = stringResource(id = R.string.delete_all_action),
                        style = Typography.titleSmall
                    )
                },
                onClick = {
                    expanded = false
                    onDeleteClicked()
                }
            )
        }
    }
}

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {
    var trailingIconState by remember {
        mutableStateOf(TrailingIconState.READY_TO_DELETE)
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_APP_BAR_HEIGHT),
        shadowElevation = TOP_APP_BAR_ELEVATION,
        color = topAppBarBackgroundColor
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            placeholder = {
                Text(
                    modifier = Modifier
                        .alpha(alpha = LocalContentColor.current.alpha),
                    text = stringResource(id = R.string.search_placeholder),
                    color = Color.White
                )
            },
            textStyle = TextStyle(
                color = topAppBarContentColor,
                fontSize = Typography.bodySmall.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier
                        .alpha(alpha = LocalContentColor.current.alpha),
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(id = R.string.search_tasks),
                        tint = topAppBarContentColor
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        when (trailingIconState) {
                            TrailingIconState.READY_TO_DELETE -> {
                                onTextChange("")
                                trailingIconState = TrailingIconState.READY_TO_CLOSE
                            }

                            TrailingIconState.READY_TO_CLOSE -> {
                                if (text.isNotBlank()) {
                                    onTextChange("")
                                } else {
                                    onCloseClicked()
                                    trailingIconState = TrailingIconState.READY_TO_DELETE
                                }
                            }
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(id = R.string.close_button),
                        tint = topAppBarContentColor
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.colors(
                cursorColor = topAppBarContentColor,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}

@Composable
@Preview
private fun DefaultListAppBarPreview() {
    DefaultListAppBar(onSearchClicked = {}, onSortAction = {}, onDeleteClicked = {})
}

@Composable
@Preview
private fun SearchAppBarPreview() {
    SearchAppBar(
        text = "Search",
        onTextChange = {},
        onCloseClicked = {},
        onSearchClicked = {}
    )
}