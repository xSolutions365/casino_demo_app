package com.example.mycasino.feature.cards.slots

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycasino.core.presentation.CardUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SlotsCardViewModel(private val getSlotsCardUseCase: GetSlotsCardUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow<CardUiState<SlotsData>>(CardUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                getSlotsCardUseCase().collect { slotsData ->
                    _uiState.value = CardUiState.Success(slotsData)
                }
            } catch (e: Exception) {
                _uiState.value = CardUiState.Error(e.message ?: "Failed to load slots data")
            }
        }
    }
}
