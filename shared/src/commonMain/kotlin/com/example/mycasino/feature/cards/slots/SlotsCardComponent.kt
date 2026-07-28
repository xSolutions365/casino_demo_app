package com.example.mycasino.feature.cards.slots

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mycasino.core.presentation.CardComponent
import com.example.mycasino.core.presentation.CardUiState
import org.koin.compose.viewmodel.koinViewModel

class SlotsCardComponent : CardComponent {
    override val id: String
        get() = "slots_card_feature"

    @Composable
    override fun Content() {
        val viewModel: SlotsCardViewModel = koinViewModel()
        val state by viewModel.uiState.collectAsStateWithLifecycle()

        Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Box(modifier = Modifier.padding(16.dp).height(100.dp)) {
                when (val result = state) {
                    is CardUiState.Loading -> CircularProgressIndicator()
                    is CardUiState.Error -> Text(
                        "Error: ${result.message}",
                        color = MaterialTheme.colorScheme.error
                    )

                    is CardUiState.Success -> {
                        Column {
                            Text(result.data.title, style = MaterialTheme.typography.titleMedium)
                            Text(
                                "Jackpot: ${result.data.jackpotValue}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}