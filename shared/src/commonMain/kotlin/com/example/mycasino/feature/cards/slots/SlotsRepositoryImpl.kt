package com.example.mycasino.feature.cards.slots

import com.example.mycasino.core.di.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SlotsRepositoryImpl(private val client: HttpClient) : SlotsRepository {
    override suspend fun getSlotsCardData(): Flow<SlotsData> {
        val result = client.get("$BASE_URL/slots").body<SlotsData>()
        return flowOf(result)
    }
}
