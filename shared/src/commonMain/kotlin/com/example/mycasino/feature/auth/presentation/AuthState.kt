package com.example.mycasino.feature.auth.presentation

import com.example.mycasino.feature.auth.domain.models.User

sealed interface AuthState {
    object Idle : AuthState
    object Loading : AuthState
    data class Success(val user: User) : AuthState
    data class Error(val message: String) : AuthState
}