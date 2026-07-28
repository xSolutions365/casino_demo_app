package com.example.mycasino.feature.cards.poker

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val pokerModule = module {
    single<PokerRepository> { PokerRepositoryImpl(get()) }
    factory { GetPokerCardUseCase(get()) }
    viewModel { PokerCardViewModel(get()) }

}