package com.example.mycasino.feature.lobby.domain.usecase

import com.example.mycasino.feature.lobby.domain.models.CasinoCard
import com.example.mycasino.feature.lobby.domain.repository.LobbyRepository
import kotlinx.coroutines.flow.Flow

class GetCasinoCardsUseCase(private val repository: LobbyRepository) {
    suspend operator fun invoke(): Flow<List<CasinoCard>> = repository.getCasinoCards()
}
