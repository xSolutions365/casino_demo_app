package com.example.mycasino.feature.lobby.data

import com.example.mycasino.core.di.BASE_URL
import com.example.mycasino.feature.lobby.domain.models.CasinoCard
import com.example.mycasino.feature.lobby.domain.repository.LobbyRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlin.time.Duration.Companion.seconds

class LobbyRepositoryImpl(
    private val client: HttpClient
) : LobbyRepository {
    override suspend fun getCasinoCards(): Flow<List<CasinoCard>> {
        delay(1.seconds)
        val result = client.post("${BASE_URL}/cards/fetch").body<List<CasinoCard>>()
        return flowOf(
            result
        )
    }
}