package com.luxsh.myapplication.room


/**
 * Created by Pinal Patel on 11/08/2021.
 * LuxshTech
 */


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShowNotes(items: List<Note>, onNodeRemoved: (Note) -> Unit) {
    LazyColumn {

        items(items) {
            Row {
                Text(
                    text = it.content,
                    modifier = Modifier
                        .padding(16.dp, 4.dp, 4.dp, 4.dp)
                        .weight(1f, true)
                        .align(Alignment.CenterVertically)
                )
                TextButton(
                    onClick = {
                        onNodeRemoved(it)
                    },
                    modifier = Modifier
                        .padding(4.dp, 4.dp, 16.dp, 4.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    Icon(
                        contentDescription = null,
                        imageVector = Icons.Filled.Delete,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}