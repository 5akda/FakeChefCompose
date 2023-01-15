package iam5akda.fakechef_compose.game.view.lobby

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import iam5akda.fakechef_compose.design_system.theme.FakeChefTheme
import iam5akda.fakechef_compose.game.model.GameLobbyData
import iam5akda.fakechef_compose.game.view.components.ExitLobbyDialog

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun LobbyScreen(
    viewModel: LobbyViewModel = hiltViewModel(),
    backToRegister: () -> Unit
) {
    val lobbyUiState by viewModel.lobbyUiState.collectAsStateWithLifecycle()
    val exitLobbyState by viewModel.exitLobbyState.collectAsStateWithLifecycle(false)

    LobbyContentLayout(
        lobbyUiState = lobbyUiState,
        onConfirmExit = viewModel::leaveRoom
    )

    if (exitLobbyState) {
        backToRegister.invoke()
    }
}

@Composable
private fun LobbyContentLayout(
    lobbyUiState: LobbyUiState,
    onConfirmExit: () -> Unit
) {
    var isShowExitDialog by remember { mutableStateOf(false) }

    FakeChefTheme {
        if (isShowExitDialog) {
            ExitLobbyDialog(
                onDismiss = { isShowExitDialog = false },
                onClickYes = onConfirmExit
            )
        }
        when (lobbyUiState) {
            is LobbyUiState.Loading -> {
                LobbyLoadingLayout(
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            is LobbyUiState.Success -> {
                LobbySuccessLayout(
                    modifier = Modifier
                        .fillMaxSize(),
                    gameLobbyData = lobbyUiState.lobbyData
                )
            }
        }
    }

    BackHandler(enabled = true) {
        isShowExitDialog = true
    }
}

@Composable
private fun LobbyLoadingLayout(modifier: Modifier) {
    Box(modifier = modifier) {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .padding(64.dp),
            color = MaterialTheme.colors.primary,
            strokeWidth = 24.dp,
        )
    }
}

@Composable
private fun LobbySuccessLayout(modifier: Modifier, gameLobbyData: GameLobbyData) {
    Box(modifier = modifier) {
        Text(text = gameLobbyData.host.orEmpty())
    }
}