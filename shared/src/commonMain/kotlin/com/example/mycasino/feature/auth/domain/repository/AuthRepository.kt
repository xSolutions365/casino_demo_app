package com.example.mycasino.feature.auth.domain.repository

import com.example.mycasino.feature.auth.domain.models.User

interface AuthRepository {
    suspend fun register(username: String, email: String): User
    suspend fun login(email: String): User?
    suspend fun logout()
}
