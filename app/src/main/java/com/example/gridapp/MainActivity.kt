package com.example.gridapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gridapp.data.DataSource
import com.example.gridapp.model.Topic
import com.example.gridapp.ui.theme.GridAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GridAppTheme {
                TopicGrid()

            }
        }
    }
}

@Composable
fun CardCompose(topic: Topic) {
    Card(modifier = Modifier.padding(8.dp)) {
        Row {
            // Use the topic.imageResId here to dynamically load the image
            Image(
                modifier = Modifier.size(68.dp),
                painter = painterResource(topic.imageResId), // Use the topic's image resource
                contentDescription = stringResource(topic.titleResId) // Provide meaningful description
            )
            Column(modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp)) {
                Text(
                    text = stringResource(topic.titleResId),
                    style = MaterialTheme.typography.bodyMedium,// Display the title
                )
                Row {
                    Image(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = "${topic.count}", // Correct way to display count
                    )
                }
            }
        }
    }
}

@Composable
fun TopicGrid() {
    val topics = DataSource.loadTopics()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),  // 2 columns in the grid
        contentPadding = PaddingValues(16.dp), // Add padding around the grid
        modifier = Modifier.fillMaxSize()
    ) {
        items(topics.size) { index ->
            CardCompose(topics[index])
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TopicGrid()
}

