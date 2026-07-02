package com.example.mycasino.core.di

import com.example.mycasino.core.database.DatabaseDriverFactory
import com.example.mycasino.database.AppDatabase
import org.koin.dsl.module

val databaseModule = module {

    single<AppDatabase> {
        val driverFactory: DatabaseDriverFactory = get()
        AppDatabase(driverFactory.createDriver())
    }

}