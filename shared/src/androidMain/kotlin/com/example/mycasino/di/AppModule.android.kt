package com.example.mycasino.di

import com.example.mycasino.core.analytics.Analytics
import com.example.mycasino.core.analytics.AndroidAnalytics
import com.example.mycasino.core.database.AndroidDatabaseDriverFactory
import com.example.mycasino.core.database.DatabaseDriverFactory
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val platformModule = module {
    single<DatabaseDriverFactory> { AndroidDatabaseDriverFactory(get()) }
    single { FirebaseAnalytics.getInstance(androidContext()) }
    single<Analytics> { AndroidAnalytics(get()) }
}