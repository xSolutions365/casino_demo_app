package com.example.mycasino.feature.auth.domain.usecase

import com.example.mycasino.feature.auth.domain.models.User
import com.example.mycasino.feature.auth.domain.repository.AuthRepository

class RegisterUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(username: String, email: String): User =
        authRepository.register(username, email)
}