package com.techtribeservices.watchme.ui.viewModels

data class MovieState(
    val movies: List<String> = emptyList(),
    val isLoading: Boolean = true
)
