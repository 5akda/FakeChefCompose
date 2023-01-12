package iam5akda.fakechef_compose.home.view.history

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType
import iam5akda.fakechef_compose.common.extension.uriEncode

internal fun NavGraphBuilder.historyRoute() {
    this.composable(
        route = "history/{message}",
        arguments = listOf(
            navArgument("message") {
                type = NavType.Companion.StringType
            }
        )
    ) {
        HistoryScreen()
    }
}

internal fun NavController.directionToHistory(message: String) {
    val encodeMessage = message.uriEncode()
    this.navigate("history/$encodeMessage")
}