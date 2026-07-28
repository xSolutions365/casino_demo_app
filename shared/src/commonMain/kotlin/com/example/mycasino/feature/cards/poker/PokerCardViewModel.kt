package com.example.mycasino.feature.cards.poker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycasino.core.presentation.CardUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokerCardViewModel(private val getPokerCardUseCase: GetPokerCardUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow<CardUiState<PokerData>>(CardUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                getPokerCardUseCase().collect { pokerData ->
                    _uiState.value = CardUiState.Success(pokerData)
                }
            } catch (e: Exception) {
                _uiState.value = CardUiState.Error(e.message ?: "Failed to load poker data")
            }
        }
    }
}