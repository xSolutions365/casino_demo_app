package com.example.mycasino.feature.lobby.presentation

import com.example.mycasino.core.presentation.CardComponent

sealed interface LobbyState {
    object Idle : LobbyState
    object Loading : LobbyState
    data class Success(val cards: List<CardComponent>) : LobbyState
    data class Error(val message: String) : LobbyState
}