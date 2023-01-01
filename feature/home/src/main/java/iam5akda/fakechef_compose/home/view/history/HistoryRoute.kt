package iam5akda.fakechef_compose.home.view.history

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType

internal fun NavGraphBuilder.historyRoute() {
    this.composable(
        route = "history/{message}",
        arguments = listOf(
            navArgument("message") {
                type = NavType.Companion.StringType
            }
        )
    ) {
        HistoryScreeen(
            argsBundle = it.arguments
        )
    }
}

internal fun NavController.directionToHistory(message: String) {
    this.navigate("history/$message")
}