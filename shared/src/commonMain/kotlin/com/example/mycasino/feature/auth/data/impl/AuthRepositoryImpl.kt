package com.example.mycasino.feature.auth.data.impl

import com.example.mycasino.database.AppDatabase
import com.example.mycasino.feature.auth.domain.models.User
import com.example.mycasino.feature.auth.domain.repository.AuthRepository
import io.ktor.client.HttpClient
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds
import kotlin.uuid.Uuid

class AuthRepositoryImpl(
    private val client: HttpClient,
    private val database: AppDatabase
) : AuthRepository {

    override suspend fun register(username: String, email: String, password: String): User {
        delay(1.seconds)
        return User(Uuid.random(), username, email)
    }

    override suspend fun login(
        email: String,
        password: String
    ): User? {
        delay(1.seconds)
        return User(Uuid.random(), "username", email)
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

}