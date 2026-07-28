package com.example.mycasino.feature.cards.slots

import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
data class SlotsData(val cardId: Uuid, val title: String, val jackpotValue: String)



