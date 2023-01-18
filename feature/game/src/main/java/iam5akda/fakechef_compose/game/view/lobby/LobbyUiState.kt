package iam5akda.fakechef_compose.game.view.lobby

import iam5akda.fakechef_compose.game.model.GameLobbyData

sealed interface LobbyUiState {
    object Loading : LobbyUiState

    data class Idle(
        val lobbyData: GameLobbyData,
        val roomCode: String,
        val tempUserId: String
    ) : LobbyUiState

    data class Ordering(
        val roomCode: String,
        val tempUserId: String
    ) : LobbyUiState

    object Kicked : LobbyUiState
}