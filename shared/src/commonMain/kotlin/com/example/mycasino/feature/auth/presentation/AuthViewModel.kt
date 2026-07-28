package com.example.mycasino.feature.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycasino.feature.auth.domain.usecase.LoginUseCase
import com.example.mycasino.feature.auth.domain.usecase.RegisterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val registerUseCase: RegisterUseCase, private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthState>(AuthState.Idle)
    val uiState = _uiState.asStateFlow()

    fun login(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = AuthState.Loading

            try {
                if (!(email.isValidEmail())) {
                    _uiState.value = AuthState.Error("Invalid email")
                    return@launch
                }

                val user = loginUseCase(email)
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

    fun register(name: String, email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = AuthState.Loading
            try {
                if (!(email.isValidEmail())) {
                    _uiState.value = AuthState.Error("Invalid email")
                    return@launch
                }
                val user = registerUseCase(name, email)
                _uiState.value = AuthState.Success(user)

            } catch (e: Exception) {
                _uiState.value = AuthState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun resetState() {
        _uiState.value = AuthState.Idle
    }

    private fun String.isValidEmail(): Boolean {
        val emailRegex = """^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$""".toRegex()

        return this.matches(emailRegex)
    }
}