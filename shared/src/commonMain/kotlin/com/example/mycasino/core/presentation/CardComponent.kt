package com.example.mycasino.core.presentation

import androidx.compose.runtime.Composable

interface CardComponent {
    val id: String

    @Composable
    fun Content()

}