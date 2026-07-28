package com.example.mycasino.feature.auth.data

import com.example.mycasino.core.analytics.Analytics
import com.example.mycasino.core.di.BASE_URL
import com.example.mycasino.database.AppDatabase
import com.example.mycasino.feature.auth.domain.models.User
import com.example.mycasino.feature.auth.domain.models.toDomain
import com.example.mycasino.feature.auth.domain.models.toIdString
import com.example.mycasino.feature.auth.domain.repository.AuthRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.http.HttpStatusCode

class AuthRepositoryImpl(
    private val client: HttpClient,
    private val database: AppDatabase,
    private val analytics: Analytics
) : AuthRepository {

    override suspend fun login(email: String): User? {
        // analytics.logEvent(AnalyticsEvent.LOGIN.name, mapOf("email" to email))

        // Check for existing user
        val cachedUser = database.userQueries.findUserByEmail(email).executeAsOneOrNull()
        if (cachedUser != null) {
            return cachedUser.toDomain()
        }

        // Fetch from remote
        val response = client.post("${BASE_URL}/login") {
            url {
                parameters.append("email", email)
            }
        }

        val remoteUser = if (response.status == HttpStatusCode.OK) {
            response.body<User>()
        } else {
            null
        }

        // Cache the remote result
        remoteUser?.let {
            database.userQueries.insertUser(
                id = remoteUser.toIdString(),
                username = remoteUser.name,
                email = remoteUser.email
            )
        }

        return remoteUser
    }

    override suspend fun register(username: String, email: String): User {

        database.userQueries.findUserByEmail(email).executeAsOneOrNull()?.let {
            throw Exception("User already exist!")
        }

        val remoteUser = client.post("${BASE_URL}/register") {
            url {
                parameters.append("username", username)
                parameters.append("email", email)
            }
        }.body<User>()

        database.userQueries.apply {
            transaction {
                insertUser(
                    id = remoteUser.toIdString(),
                    username = remoteUser.name,
                    email = remoteUser.email
                )
            }
        }

        return remoteUser
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

}