package com.example.mycasino.core.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import kotlin.uuid.Uuid

const val BASE_URL = "https://mycasino.com"

val networkModule = module {
    single<HttpClient> {
        HttpClient(MockEngine) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            engine {
                addHandler { request ->
                    val path = request.url.encodedPath
                    val params = request.url.parameters

                    when {
                        path.endsWith("/cards/fetch") -> {
                            val mockJsonResponse = """
                                    [
                                    {"id": "slots_card_feature"}, 
                                    {"id": "blackjack_card_feature"},
                                    {"id": "poker_card_feature"}
                                     ]
                                    """.trimIndent()

                            respond(
                                content = mockJsonResponse,
                                status = HttpStatusCode.OK,
                                headers = headersOf(HttpHeaders.ContentType, "application/json")
                            )
                        }

                        path.endsWith("/slots") -> {
                            val mockJsonResponse = """
                            {
                              "cardId": "5f3c1a84-2bd8-4903-b514-7d9a6e11c82b",
                              "title": "Slots Card Component",
                              "jackpotValue": "1000000$"
                            }
                        """.trimIndent()
                            respond(
                                content = mockJsonResponse,
                                status = HttpStatusCode.OK,
                                headers = headersOf(HttpHeaders.ContentType, "application/json")
                            )
                        }

                        path.endsWith("/poker") -> {
                            val mockJsonResponse = """
                            {
                              "cardId": "5f3c1a84-2bd8-4903-b514-7d9a6e11c82c",
                              "title": "Poker Card Component",
                              "description": "Play Texas Poker with real people"
                            }
                        """.trimIndent()
                            respond(
                                content = mockJsonResponse,
                                status = HttpStatusCode.OK,
                                headers = headersOf(HttpHeaders.ContentType, "application/json")
                            )
                        }

                        path.endsWith("/register") -> {
                            val username = params["username"]
                            val email = params["email"]
                            val mockJsonResponse = """
                            {
                              "id": "${Uuid.random()}",
                              "name": "$username",
                              "email": "$email"
                            }
                        """.trimIndent()
                            respond(
                                content = mockJsonResponse,
                                status = HttpStatusCode.OK,
                                headers = headersOf(HttpHeaders.ContentType, "application/json")
                            )
                        }

                        path.endsWith("/login") -> {
                            respond(
                                content = "Invalid credentials.",
                                status = HttpStatusCode.Unauthorized,
                                headers = headersOf(HttpHeaders.ContentType, "application/json")
                            )
                        }

                        else -> {
                            respond(
                                content = "{\"error\": \"Route not found\"}",
                                status = HttpStatusCode.NotFound,
                                headers = headersOf(HttpHeaders.ContentType, "application/json")
                            )
                        }
                    }
                }
            }
        }
    }
}