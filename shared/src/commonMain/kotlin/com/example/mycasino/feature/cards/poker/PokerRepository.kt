package com.example.mycasino.feature.cards.poker

import kotlinx.coroutines.flow.Flow

interface PokerRepository {
    suspend fun getPokerCard(): Flow<PokerData>
}