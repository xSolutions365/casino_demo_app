package com.example.mycasino.di

import com.example.mycasino.core.database.DatabaseDriverFactory
import org.koin.dsl.module

actual val platformModule = module {
    single {
        DatabaseDriverFactory(context = get())
    }
}