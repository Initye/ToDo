package com.example.to_do

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.to_do.ui.theme.Adlam
import com.example.to_do.ui.theme.ToDoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoTheme {
                Surface (
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.systemBars), //Responsive to system UI elements //WindowInsets.safeDrawing / ime -Keyboard also
                    color = MaterialTheme.colorScheme.background

                ) {
                    ToDoApp()
                }
            }
        }
    }
}


@Composable
fun ToDoApp(modifier: Modifier = Modifier) {
    val items = remember { mutableStateListOf<String>() }
    var showDialog by remember { mutableStateOf(false) }

    Box { //Box for free positioning
        Column {
            Text(
                modifier = modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = stringResource(R.string.app_name),
                fontFamily = Adlam,
                style = MaterialTheme.typography.headlineLarge,
            )
            LazyColumn {
                items(items) { itemText ->
                    ListElement(
                        text = itemText
                    )
                }
            }
        }
        addButton(
            modifier = modifier
                .size(48.dp)
                .padding(start = 8.dp)
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            onClick = {
                showDialog = true
            })
    }

    if(showDialog) {
        AddToDoDialog(
            onDismiss = { showDialog = false },
            onAdd = { newItem ->
                items.add(newItem)
            showDialog = false }
        )
    }
}

//Pop up of adding new element
@Composable
fun AddToDoDialog(modifier: Modifier = Modifier, onDismiss: () -> Unit, onAdd: (String) -> Unit ) {
    var content by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add new Todo") },
        text = {
            TextField(
                value = content,
                onValueChange = { content = it }
            )
        },
        confirmButton = {
            Button(onClick = { if(content.isNotBlank()) {
                onAdd(content)
                }
            }) {
                Text("Add")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ToDoTheme {
        ToDoApp()
    }
}