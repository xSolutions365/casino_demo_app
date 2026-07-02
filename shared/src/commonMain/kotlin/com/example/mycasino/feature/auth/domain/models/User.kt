package com.example.mycasino.feature.auth.domain.models

import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
data class User(
    val id: Uuid,
    val name: String,
    val email: String,
)
