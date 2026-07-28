package com.example.mycasino.feature.cards.poker

import com.example.mycasino.core.di.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlin.time.Duration.Companion.seconds

class PokerRepositoryImpl(
    private val client: HttpClient
) : PokerRepository {
    override suspend fun getPokerCard(): Flow<PokerData> {
        delay(1.seconds)
        val result = client.post("$BASE_URL/poker").body<PokerData>()
        return flowOf(
            result
        )
    }
}