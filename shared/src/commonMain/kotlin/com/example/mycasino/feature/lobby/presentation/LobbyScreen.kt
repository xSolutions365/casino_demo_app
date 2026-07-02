package com.example.mycasino.feature.lobby.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridCells.*
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mycasino.feature.auth.domain.models.User
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
                title = { Text("Welcome, $username") },
                actions = { Button(onClick = onLogout) { Text("Logout") } }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            when (val result = state) {
                is LobbyState.Loading -> CircularProgressIndicator(
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )

                is LobbyState.Error -> Text(
                    result.message,
                    modifier = Modifier.align(Alignment.Center)
                )

                is LobbyState.Success -> {
                    LazyVerticalGrid(
                        columns = Fixed(2),
                        contentPadding = PaddingValues(8.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(result.cards) { card ->
                            Card(modifier = Modifier.padding(8.dp).fillMaxWidth()) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(card.name, style = MaterialTheme.typography.titleMedium)
                                    Text(card.name, style = MaterialTheme.typography.bodySmall)
                                }
                            }
                        }
                    }
                }

                LobbyState.Idle -> {

                }
            }
        }
    }
}