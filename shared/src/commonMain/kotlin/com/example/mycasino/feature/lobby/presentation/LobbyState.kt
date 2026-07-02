package com.example.mycasino.feature.lobby.presentation

import com.example.mycasino.feature.lobby.domain.models.CasinoCard

sealed interface LobbyState {
    object Idle : LobbyState
    object Loading : LobbyState
    data class Success(val cards: List<CasinoCard>) : LobbyState
    data class Error(val message: String) : LobbyState
}