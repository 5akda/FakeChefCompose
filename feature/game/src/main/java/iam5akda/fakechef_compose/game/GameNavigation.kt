package iam5akda.fakechef_compose.game

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import iam5akda.fakechef_compose.game.view.lobby.directionToLobby
import iam5akda.fakechef_compose.game.view.lobby.lobbyRoute
import iam5akda.fakechef_compose.game.view.register.backToRegister
import iam5akda.fakechef_compose.game.view.register.registerRoute

@Composable
fun GameNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "register"
    ) {
        registerRoute(
            directionToLobby = navController::directionToLobby
        )
        lobbyRoute(
            directionToFoodOrder = {},
            backToRegister = navController::backToRegister
        )
    }
}