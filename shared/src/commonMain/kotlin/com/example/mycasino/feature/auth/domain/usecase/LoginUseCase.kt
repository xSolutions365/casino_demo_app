package com.example.mycasino.feature.auth.domain.usecase

import com.example.mycasino.feature.auth.domain.models.User
import com.example.mycasino.feature.auth.domain.repository.AuthRepository

class LoginUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String): User? = repository.login(email)
}