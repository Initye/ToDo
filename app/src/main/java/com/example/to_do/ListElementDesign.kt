package com.example.to_do

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.to_do.ui.theme.ToDoTheme


@Composable
fun ListElement(modifier: Modifier = Modifier, text: String) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(46.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxSize()
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.displayLarge,
                modifier = modifier
                    .padding(start = 30.dp)
            )
            Spacer(Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.checkbox_unchecked),
                contentDescription = "Unchecked",
                modifier = modifier
                    .size(35.dp)
                    .padding(end = 12.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ElementPreview() {
    ToDoTheme {
        ListElement(text = "asd")
    }
}