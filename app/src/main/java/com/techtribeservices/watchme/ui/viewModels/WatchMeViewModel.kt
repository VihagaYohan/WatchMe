package com.techtribeservices.watchme.ui.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class WatchMeViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(MovieState())
    val uiState: StateFlow<MovieState> = _uiState.asStateFlow()

    init {
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch {
            delay(4000)
            _uiState.value = MovieState(
                // movies = List(1000) {generateMovieName()},
                isLoading = false
            )
        }
    }

    fun generateMovieName(): String {
        val adjectives = listOf("Dark", "Mysterious", "Lost", "Forgotten", "Silent", "Hidden", "Eternal", "Secret", "Last", "Infinite")
        val nouns = listOf("Journey", "Dream", "Empire", "Legacy", "Night", "World", "Revenge", "Destiny", "Shadow", "Mystery")

        // Generate a random movie name by combining an adjective and noun
        val adjective = adjectives[Random.nextInt(adjectives.size)]
        val noun = nouns[Random.nextInt(nouns.size)]
        return "$adjective $noun"
    }
}