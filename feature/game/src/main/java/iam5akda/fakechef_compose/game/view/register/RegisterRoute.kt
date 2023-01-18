package iam5akda.fakechef_compose.game.view.register

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal fun NavGraphBuilder.registerRoute(
    directionToLobby: (code: String, name: String) -> Unit
) {
    this.composable(
        route = "register"
    ) {
        RegisterScreen(onClickJoin = directionToLobby)
    }
}

internal fun NavController.backToRegister() {
    this.popBackStack(
        destinationId = this.graph.findStartDestination().id,
        inclusive = false
    )
}