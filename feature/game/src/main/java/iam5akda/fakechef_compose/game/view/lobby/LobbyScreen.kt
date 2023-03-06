package iam5akda.fakechef_compose.game.view.lobby

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import iam5akda.fakechef_compose.design_system.component.GiantLoadingLayout
import iam5akda.fakechef_compose.design_system.theme.FakeChefTheme
import iam5akda.fakechef_compose.design_system.util.ComposeTools
import iam5akda.fakechef_compose.game.R
import iam5akda.fakechef_compose.game.model.GameLobbyData
import iam5akda.fakechef_compose.game.model.PlayerData
import iam5akda.fakechef_compose.game.view.component.ExitLobbyDialog
import iam5akda.fakechef_compose.game.view.component.PlayerItemLayout

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun LobbyScreen(
    viewModel: LobbyViewModel = hiltViewModel(),
    backToRegister: () -> Unit
) {
    val lobbyUiState by viewModel.lobbyUiState.collectAsStateWithLifecycle()

    LobbyContentLayout(
        lobbyUiState = lobbyUiState,
        onConfirmExit = viewModel::leaveRoom,
        onClickStart = viewModel::hostStartGame,
        backToRegister = backToRegister
    )
}

@Composable
private fun LobbyContentLayout(
    lobbyUiState: LobbyUiState,
    onConfirmExit: () -> Unit,
    onClickStart: () -> Unit,
    backToRegister: () -> Unit
) {
    var isShowExitDialog by remember {
        mutableStateOf(false)
    }

    FakeChefTheme {
        if (isShowExitDialog) {
            ExitLobbyDialog(
                onDismiss = { isShowExitDialog = false },
                onClickYes = onConfirmExit
            )
        }
        BackHandler(enabled = true) {
            isShowExitDialog = true
        }
    }

    FakeChefTheme {
        when (lobbyUiState) {
            is LobbyUiState.Loading -> {
                GiantLoadingLayout(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background)
                )
            }
            is LobbyUiState.Idle -> {
                LobbySuccessLayout(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background),
                    gameLobbyData = lobbyUiState.lobbyData,
                    roomCode = lobbyUiState.roomCode,
                    tempUserId = lobbyUiState.tempUserId,
                    onClickStart = onClickStart
                )
            }
            is LobbyUiState.Ordering -> {
                Toast.makeText(LocalContext.current, "Test:: Ordering", Toast.LENGTH_SHORT).show()
            }
            else -> {
                backToRegister.invoke()
            }
        }
    }
}

@Composable
private fun LobbySuccessLayout(
    modifier: Modifier,
    gameLobbyData: GameLobbyData,
    onClickStart: () -> Unit,
    roomCode: String,
    tempUserId: String
) {
    var isGameStarting by remember {
        mutableStateOf(false)
    }
    val hostStartGame = remember {
        {
            isGameStarting = true
            onClickStart.invoke()
        }
    }

    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .align(Alignment.TopStart)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(R.string.lobby_room_code, roomCode),
                fontSize = 18.sp,
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                text = stringResource(R.string.lobby_host_name, gameLobbyData.getHostPlayer().name),
                fontSize = 18.sp,
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                items(gameLobbyData.getPlayerList()) { player ->
                    PlayerItemLayout(
                        modifier = Modifier
                            .padding(top = 4.dp, start = 4.dp, end = 4.dp)
                            .alpha(0.7f)
                            .fillMaxWidth(),
                        playerData = player
                    )
                }
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 24.dp)
                .align(Alignment.BottomCenter),
            onClick = hostStartGame,
            enabled = gameLobbyData.isHost(tempUserId) && !isGameStarting
        ) {
            Text(
                modifier = Modifier
                    .padding(vertical = 2.dp),
                text = stringResource(id = R.string.button_start_game),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h6
            )
        }
    }
}

@Preview(
    uiMode = ComposeTools.PREVIEW_IN_NIGHT,
    showBackground = true,
    device = Devices.PIXEL_3
)
@Composable
private fun LobbySuccessLayoutPreview() {
    val data = GameLobbyData(
        players = hashMapOf(
            Pair("", PlayerData(name = "Nabuki"))
        )
    )
    FakeChefTheme {
        LobbySuccessLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            gameLobbyData = data,
            onClickStart = {},
            roomCode = "987654",
            tempUserId = ""
        )
    }
}