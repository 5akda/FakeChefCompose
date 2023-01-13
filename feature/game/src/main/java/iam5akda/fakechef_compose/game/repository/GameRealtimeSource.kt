package iam5akda.fakechef_compose.game.repository

import iam5akda.fakechef_compose.game.model.GameLobbyData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GameRealtimeSource @Inject constructor() : GameRepository {

    override fun registerPlayer(roomCode: String, yourName: String): Flow<Unit> {
        return flow {
            //TODO
        }
    }

    override fun streamLobbySession(): Flow<GameLobbyData> {
        return flow {
            // TODO
        }
    }
}