package com.example.mycasino.feature.lobby.domain.repository

import com.example.mycasino.feature.lobby.domain.models.CasinoCard
import kotlinx.coroutines.flow.Flow

interface LobbyRepository {
    suspend fun getCasinoCards(): Flow<List<CasinoCard>>
}