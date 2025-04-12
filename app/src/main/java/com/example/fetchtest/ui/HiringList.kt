package com.example.fetchtest.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fetchtest.ui.theme.FetchTestTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HiringList(modifier: Modifier = Modifier) {
    val sections = listOf("A", "B", "C", "D", "E", "F", "G")

    LazyColumn(modifier = modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)) {
        sections.forEach { section ->
            stickyHeader {
                Text(
                    text = "Section $section",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(8.dp)
                )
            }
            items(10) {
                HiringListItem("Item $it from the section $section")
            }
        }
    }
}

@Composable
fun HiringListItem(text: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.surface)
            .height(56.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(text = text)
    }
}

@Composable
@Preview(showBackground = true, heightDp = 480)
fun HiringListPreview() {
    FetchTestTheme {
        HiringList()
    }
}