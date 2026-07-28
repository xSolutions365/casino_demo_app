package com.example.mycasino.feature.lobby.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mycasino.shared.generated.resources.Res
import mycasino.shared.generated.resources.app_background
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import kotlin.uuid.Uuid

@Composable
fun LobbyScreen(
    username: String,
    onCardClick: (Uuid) -> Unit,
    onLogout: () -> Unit,
    viewModel: LobbyViewModel = koinViewModel()
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = Color.Black),
                title = { Text("Welcome, $username", color = Color.White) },
                actions = {
                    Button(
                        onClick = onLogout,
                    ) { Text("Logout") }
                }
            )
        }
    ) { paddingValues ->

        Box(modifier = Modifier.fillMaxSize()) {

            Image(
                painter = painterResource(Res.drawable.app_background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                when (val result = state) {
                    is LobbyState.Loading -> CircularProgressIndicator(
                        modifier = Modifier.align(
                            Alignment.Center
                        ),
                        color = Color.White
                    )

                    is LobbyState.Error -> Text(
                        "Error: ${result.message}",
                        modifier = Modifier.align(Alignment.Center)
                    )

                    is LobbyState.Success -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            itemsIndexed(
                                items = result.cards,
                                key = { index, card -> "${card.id}_$index" }) { _, card ->
                                card.Content()
                            }
                        }
                    }

                    LobbyState.Idle -> {}
                }
            }
        }
    }
}