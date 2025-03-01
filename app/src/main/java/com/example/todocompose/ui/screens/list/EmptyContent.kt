package com.example.todocompose.ui.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.todocompose.R
import com.example.todocompose.ui.theme.ICON_SIZE
import com.example.todocompose.ui.theme.MediumGray
import com.example.todocompose.ui.theme.Typography

@Composable
fun EmptyContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(ICON_SIZE),
            painter = painterResource(id = R.drawable.ic_sad_face),
            contentDescription = stringResource(id = R.string.sad_face_icon),
            tint = MediumGray
        )
        Text (
            text = stringResource(id = R.string.empty_content),
            color = MediumGray,
            fontWeight = FontWeight.Bold,
            fontSize = Typography.titleSmall.fontSize
        )
    }
}

@Composable
@Preview
fun EmptyContentPreview() {
    EmptyContent()
}