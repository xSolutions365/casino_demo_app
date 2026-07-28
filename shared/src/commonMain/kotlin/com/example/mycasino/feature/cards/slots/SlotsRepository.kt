package com.example.mycasino.feature.cards.slots

import kotlinx.coroutines.flow.Flow

interface SlotsRepository {
    suspend fun getSlotsCardData(): Flow<SlotsData>
}

