package com.example.mycasino.feature.cards.poker

import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
data class PokerData(
    val cardId: Uuid,
    val title: String,
    val description: String
)
