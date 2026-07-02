package com.example.mycasino.core.database


import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.mycasino.database.AppDatabase

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        // Android requires a Context and a database name string
        return AndroidSqliteDriver(AppDatabase.Schema, context, "app_database.db")
    }
}