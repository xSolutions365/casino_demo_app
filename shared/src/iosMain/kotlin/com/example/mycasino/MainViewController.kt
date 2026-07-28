package com.example.mycasino

import androidx.compose.ui.window.ComposeUIViewController
import com.example.mycasino.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) { MainNavigation() }