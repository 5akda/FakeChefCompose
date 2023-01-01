package iam5akda.fakechef_compose.home.view.menu

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal fun NavGraphBuilder.menuRoute(
    directionToCreateRoom: () -> Unit,
    directionToHistory: (String) -> Unit
) {
    this.composable(
        route = "menu"
    ) {
        MenuScreen(
            onClickCreateRoom = directionToCreateRoom,
            onClickHistory = directionToHistory,
            onClickHelp = {}
        )
    }
}