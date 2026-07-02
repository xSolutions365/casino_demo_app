package com.example.mycasino.feature.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycasino.feature.auth.domain.usecase.LoginUseCase
import com.example.mycasino.feature.auth.domain.usecase.RegisterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val registerUseCase: RegisterUseCase, private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthState>(AuthState.Idle)
    val uiState = _uiState.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = AuthState.Loading

            try {
                if (!validateCredentials(email, password)) {
                    _uiState.value = AuthState.Error("Invalid credentials")
                    return@launch
                }

                val user = loginUseCase(email, password)
                if (user != null) {
                    _uiState.value = AuthState.Success(user)
                } else {
                    _uiState.value = AuthState.Error("Invalid credentials")
                }
            } catch (e: Exception) {
                _uiState.value = AuthState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = AuthState.Loading
            try {
                if (!validateCredentials(email, password)) {
                    _uiState.value = AuthState.Error("Invalid credentials")
                    return@launch
                }
                val user = registerUseCase(name, email, password)
            } catch (e: Exception) {
                _uiState.value = AuthState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun resetState() {
        _uiState.value = AuthState.Idle
    }

    private fun validateCredentials(email: String, password: String): Boolean {
        return email.isNotBlank() && password.isNotBlank()
    }
}