package com.example.to_do

import android.graphics.Color
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun addElement() {
    var text by remember { mutableStateOf("") }
    Row {
        inputField(text = text, onTextChange = { text = it })
        addButton(onClick = { text = "" }) // Example: Resets input field on click
    }
}


@Composable
fun inputField(modifier: Modifier = Modifier, text: String, onTextChange: (String) -> Unit) {
    TextField(
        modifier = modifier
            .height(48.dp) // Smaller height for a cleaner look
            .padding(horizontal = 12.dp),
        value = text,
        onValueChange = onTextChange,
        textStyle = MaterialTheme.typography.bodySmall.copy(fontSize = 15.sp),
        singleLine = true,
    )
}


@Composable
fun addButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Image(
        painter = painterResource(id = R.drawable.addelementicon),
        contentDescription = "Add",
        modifier = modifier
            .clickable(onClick = onClick)
    )
}

@Composable
@Preview
fun TextPreview() {
    addElement()
}