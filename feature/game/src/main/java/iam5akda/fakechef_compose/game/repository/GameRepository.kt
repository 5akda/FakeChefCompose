package iam5akda.fakechef_compose.game.repository

import iam5akda.fakechef_compose.game.model.GameLobbyData
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun registerPlayer(roomCode: String, yourName: String): Flow<String>
    fun streamLobbySession(roomCode: String): Flow<GameLobbyData>
    fun leaveRoom(roomCode: String, tempUserId: String): Flow<Unit>
}