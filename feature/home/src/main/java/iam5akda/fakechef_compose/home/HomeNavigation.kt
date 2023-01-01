package iam5akda.fakechef_compose.home

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import iam5akda.fakechef_compose.home.view.history.directionToHistory
import iam5akda.fakechef_compose.home.view.history.historyRoute
import iam5akda.fakechef_compose.home.view.menu.menuRoute

@Composable
fun HomeNavigation(
    startGameActivity: () -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "menu"
    ) {
        menuRoute(
            directionToCreateRoom = startGameActivity,
            directionToHistory = navController::directionToHistory
        )
        historyRoute()
    }
}