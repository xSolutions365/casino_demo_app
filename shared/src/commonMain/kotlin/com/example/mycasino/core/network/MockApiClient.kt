package com.example.mycasino.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object MockApiClient {
    val client = HttpClient(MockEngine) {
        // Essential step to automatically handle JSON payloads
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
            })
        }

        engine {
            addHandler { request ->
                val path = request.url.encodedPath

                when {
                    // Match a specific GET request route
                    path.endsWith("/users/1") -> {
                        val mockJsonResponse = """
                            {
                                "id": 1,
                                "name": "Jane Doe",
                                "email": "janedoe@example.com"
                            }
                        """.trimIndent()

                        respond(
                            content = mockJsonResponse,
                            status = HttpStatusCode.OK,
                            headers = headersOf(HttpHeaders.ContentType, "application/json")
                        )
                    }

                    // Add other mock endpoints here (POST, PUT, etc.)

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