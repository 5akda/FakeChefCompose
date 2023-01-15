package iam5akda.fakechef_compose.game.view.lobby

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import iam5akda.fakechef_compose.common.extension.uriEncode

internal fun NavGraphBuilder.lobbyRoute(
    directionToFoodOrder: () -> Unit,
    backToRegister: () -> Unit
) {
    this.composable(
        route = "lobby/{${LobbyRoute.ARG_ROOM_CODE}}/{${LobbyRoute.ARG_YOUR_NAME}}",
        arguments = listOf(
            navArgument(LobbyRoute.ARG_YOUR_NAME) {
                type = NavType.StringType
            }
        )
    ) {
        LobbyScreen(
            backToRegister = backToRegister
        )
    }
}

internal fun NavController.directionToLobby(code: String, name: String) {
    val encodedRoomCode = code.uriEncode()
    val encodedYourName = name.uriEncode()
    this.navigate(route = "lobby/$encodedRoomCode/$encodedYourName")
}

internal object LobbyRoute {
    const val ARG_ROOM_CODE = "roomCode"
    const val ARG_YOUR_NAME = "yourName"
}