package iam5akda.fakechef_compose.game.repository

import iam5akda.fakechef_compose.game.model.LobbySessionStatus
import kotlinx.coroutines.flow.Flow

interface HostRepository {
    fun setLobbyStatus(roomCode: String, status: LobbySessionStatus): Flow<Unit>
    fun assignFakeChef(roomCode: String, playerId: String): Flow<Unit>
    fun assignHeadChef(roomCode: String, playerId: String): Flow<Unit>
}