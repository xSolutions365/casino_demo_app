package com.example.mycasino.feature.lobby.domain.models

import kotlin.uuid.Uuid

data class CasinoCard(
    val id: Uuid,
    val name: String,
)
