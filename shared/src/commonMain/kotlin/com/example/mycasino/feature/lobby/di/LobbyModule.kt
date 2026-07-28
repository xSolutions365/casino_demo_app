package com.example.mycasino.feature.lobby.di

import com.example.mycasino.feature.lobby.data.LobbyRepositoryImpl
import com.example.mycasino.feature.lobby.domain.repository.LobbyRepository
import com.example.mycasino.feature.lobby.domain.usecase.GetCasinoCardsUseCase
import com.example.mycasino.feature.lobby.presentation.LobbyViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val lobbyModule = module {
    single<LobbyRepository> { LobbyRepositoryImpl(get()) }
    factory { GetCasinoCardsUseCase(get()) }
    viewModel { LobbyViewModel(get(), get()) }
}
