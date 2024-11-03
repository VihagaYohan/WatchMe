package com.techtribeservices.watchme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techtribeservices.watchme.ui.theme.WatchMeTheme
import com.techtribeservices.watchme.ui.viewModels.WatchMeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.techtribeservices.watchme.ui.theme.Red500

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WatchMeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MovieList(
                        modifier = Modifier.padding(innerPadding),
                        watchMeViewModel = viewModel()
                    )
                }
            }
        }
    }
}

@Composable
fun MovieList(
    modifier: Modifier = Modifier,
    watchMeViewModel: WatchMeViewModel) {

    val appState by watchMeViewModel.uiState.collectAsState()

    Column(
        modifier = modifier
    ) {
        if(appState.isLoading) {
            Box(
                modifier = modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(40.dp),
                    color = MaterialTheme.colorScheme.secondary,
                )
            }
        } else if (appState.movies.isEmpty()) {
            Text("There are no movies to show")
        } else {

            LazyColumn {
                items(appState.movies.size) { index ->
                    Text(text = "${index + 1}. ${appState.movies[index]}")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WatchMeTheme {
        MovieList(
            modifier = Modifier,
            watchMeViewModel = viewModel()
        )
    }
}