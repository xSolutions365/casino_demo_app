package com.example.mycasino.feature.cards.slots

import kotlinx.coroutines.flow.Flow

class GetSlotsCardUseCase(private val repository: SlotsRepository) {
    suspend operator fun invoke(): Flow<SlotsData> = repository.getSlotsCardData()
}