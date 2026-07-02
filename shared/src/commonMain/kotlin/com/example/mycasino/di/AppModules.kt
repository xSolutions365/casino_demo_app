package com.example.mycasino.di

import com.example.mycasino.core.di.databaseModule
import com.example.mycasino.core.di.networkModule
import com.example.mycasino.feature.auth.di.authModule
import com.example.mycasino.feature.lobby.di.lobbyModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration


expect val platformModule: Module


val appModules = listOf(
    platformModule,
    networkModule,
    databaseModule,
    authModule,
    lobbyModule,
)

fun initKoin(appDeclaration: KoinAppDeclaration? = null) {
    startKoin {
        appDeclaration?.invoke(this)
        modules(appModules)
    }
}