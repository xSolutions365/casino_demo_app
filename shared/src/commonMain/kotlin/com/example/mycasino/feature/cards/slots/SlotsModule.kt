package com.example.mycasino.feature.cards.slots

import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel


val slotsModule = module {
    single<SlotsRepository> { SlotsRepositoryImpl(get()) }
    factory { GetSlotsCardUseCase(get()) }
    viewModel { SlotsCardViewModel(get()) }
}