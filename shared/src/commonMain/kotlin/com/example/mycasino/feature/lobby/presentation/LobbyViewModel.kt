package com.example.mycasino.feature.lobby.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycasino.core.presentation.CardFactory
import com.example.mycasino.feature.lobby.domain.usecase.GetCasinoCardsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LobbyViewModel(
    private val getCasinoCardsUseCase: GetCasinoCardsUseCase,
    private val cardFactory: CardFactory
) : ViewModel() {

    private val _uiState = MutableStateFlow<LobbyState>(LobbyState.Idle)
    val uiState = _uiState.asStateFlow()

    init {
        fetchCasinoCards()
    }

    fun fetchCasinoCards() {
        viewModelScope.launch {
            _uiState.value = LobbyState.Loading
            try {
                getCasinoCardsUseCase.invoke().collect { cards ->
                    val cardsComponents = cards.mapNotNull { cardFactory.createCard(it.id) }
                    _uiState.value = LobbyState.Success(cardsComponents)
                }
            } catch (e: Exception) {
                _uiState.value = LobbyState.Error(e.message ?: "Unknown error")
            }
        }
    }
}