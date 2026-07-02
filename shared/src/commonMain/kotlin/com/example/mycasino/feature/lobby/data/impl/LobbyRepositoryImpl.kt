package com.example.mycasino.feature.lobby.data.impl

import com.example.mycasino.feature.lobby.domain.models.CasinoCard
import com.example.mycasino.feature.lobby.domain.repository.LobbyRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlin.time.Duration.Companion.seconds
import kotlin.uuid.Uuid

class LobbyRepositoryImpl : LobbyRepository {
    override suspend fun getCasinoCards(): Flow<List<CasinoCard>> {
        delay(1.seconds)
        return flowOf(
            listOf(
                CasinoCard(Uuid.random(), "Mega Slots"),
                CasinoCard(Uuid.random(), "Blackjack"),
                CasinoCard(Uuid.random(), "Roulette"),
                CasinoCard(Uuid.random(), "Texas Poker")
            )
        )
    }
}