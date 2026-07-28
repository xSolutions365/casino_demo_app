package com.example.mycasino.core.presentation

sealed interface CardUiState<out T> {
    object Loading : CardUiState<Nothing>
    data class Success<T>(val data: T) : CardUiState<T>
    data class Error(val message: String) : CardUiState<Nothing>

}