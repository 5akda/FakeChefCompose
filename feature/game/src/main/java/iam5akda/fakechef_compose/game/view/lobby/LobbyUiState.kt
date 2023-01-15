package iam5akda.fakechef_compose.game.view.lobby

import iam5akda.fakechef_compose.game.model.GameLobbyData

sealed interface LobbyUiState {
    object Loading : LobbyUiState
    data class Success(
        val lobbyData: GameLobbyData,
        val roomCode: String
    ) : LobbyUiState
}