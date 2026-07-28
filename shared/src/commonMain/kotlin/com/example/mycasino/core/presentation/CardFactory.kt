package com.example.mycasino.core.presentation

import com.example.mycasino.feature.cards.poker.PokerCardComponent
import com.example.mycasino.feature.cards.slots.SlotsCardComponent

class CardFactory {
    private val registry: Map<String, () -> CardComponent> = mapOf(
        "slots_card_feature" to { SlotsCardComponent() },
        "poker_card_feature" to { PokerCardComponent()}
    )

    fun createCard(id: String): CardComponent? {
        return registry[id]?.invoke()
    }
}