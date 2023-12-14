package com.graph.apollo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SearchField(
    onSearchButtonClick : (query : String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("Text")
    }

    Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
        TextField(
            modifier = Modifier.weight(1f),
            value = text,
            onValueChange = { text = it },
            label = { Text("Найти персонажа") },
            singleLine = true
        )

        IconButton(onClick = {
            onSearchButtonClick(text)
        }) {

            Icon(painter = rememberVectorPainter(image = Icons.Filled.Search), contentDescription = "Search")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview(){
    SearchField()
}