package com.example.mycasino

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.mycasino.feature.auth.domain.models.User
import com.example.mycasino.feature.auth.presentation.LoginScreen
import com.example.mycasino.feature.auth.presentation.RegisterScreen
import com.example.mycasino.feature.lobby.presentation.LobbyScreen
import kotlinx.serialization.Serializable
import org.koin.compose.KoinContext

@Serializable
object LoginScreen

@Serializable
object RegisterScreen

@Serializable
data class LobbyScreen(val username: String)


@Composable
fun MainNavigation() {
    KoinContext {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = LoginScreen
        ) {
            composable<LoginScreen> {
                LoginScreen(
                    onLoginSuccess = { user ->
                        navController.navigate(LobbyScreen(username = user.name))
                    },
                    onNavigateToRegister = {
                        navController.navigate(RegisterScreen)
                    }
                )
            }

            composable<RegisterScreen> {
                RegisterScreen(
                    onRegistrationSuccess = { user ->
                        navController.navigate(LobbyScreen(user.name))
                    }
                )
            }

            composable<LobbyScreen> { backStackEntry ->
                val username = backStackEntry.toRoute<LobbyScreen>().username
                LobbyScreen(
                    username = username,
                    onCardClick = { cardId -> },
                    onLogout = {
                        navController.popBackStack<LoginScreen>(inclusive = false)
                    }
                )
            }
        }
    }
}