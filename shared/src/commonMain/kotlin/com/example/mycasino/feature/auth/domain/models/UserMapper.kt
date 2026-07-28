package com.example.mycasino.feature.auth.domain.models

import com.example.mycasino.database.UserEntity
import kotlin.uuid.Uuid

fun UserEntity.toDomain(): User {
    return User(
        id = Uuid.parse(this.id),
        name = this.username,
        email = this.email
    )
}

fun User.toIdString(): String {
    return this.id.toString()
}