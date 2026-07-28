package com.example.mycasino.feature.cards.poker

import kotlinx.coroutines.flow.Flow

class GetPokerCardUseCase(private val repository: PokerRepository) {
    suspend operator fun invoke(): Flow<PokerData> = repository.getPokerCard()
}