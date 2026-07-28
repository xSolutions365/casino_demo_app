package com.example.mycasino.feature.auth.di

import com.example.mycasino.feature.auth.data.AuthRepositoryImpl
import com.example.mycasino.feature.auth.domain.repository.AuthRepository
import com.example.mycasino.feature.auth.domain.usecase.LoginUseCase
import com.example.mycasino.feature.auth.domain.usecase.RegisterUseCase
import com.example.mycasino.feature.auth.presentation.AuthViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel

val authModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get(), get()) }
    factory { LoginUseCase(get()) }
    factory { RegisterUseCase(get()) }
    viewModel { AuthViewModel(get(), get()) }

}