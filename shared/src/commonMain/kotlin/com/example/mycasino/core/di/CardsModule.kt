package com.example.mycasino.core.di

import com.example.mycasino.core.presentation.CardFactory
import org.koin.dsl.module

val cardsModule = module {
    single { CardFactory() }
}