package iam5akda.fakechef_compose.game.view.register

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import iam5akda.fakechef_compose.common.extension.uriEncode

internal fun NavGraphBuilder.registerRoute(
    directionToLobby: (code: String, name: String) -> Unit
) {
    this.composable(
        route = "register"
    ) {
        RegisterScreen(onClickJoin = directionToLobby)
    }
}

internal fun NavController.directionToLobby(code: String, name: String) {
    val encodedRoomCode = code.uriEncode()
    val encodedYourName = name.uriEncode()
    this.navigate("lobby/$encodedRoomCode/$encodedYourName")
}