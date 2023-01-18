package iam5akda.fakechef_compose.game.repository

import iam5akda.fakechef_compose.common.dispatcher.IoDispatcher
import iam5akda.fakechef_compose.data.realtime.RealtimeDatabaseUtility
import iam5akda.fakechef_compose.game.model.GameLobbyData
import iam5akda.fakechef_compose.game.model.PlayerData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject

class GameRealtimeSource @Inject constructor(
    private val realtimeDatabaseUtility: RealtimeDatabaseUtility,
    @IoDispatcher
    private val dispatcher: CoroutineDispatcher
) : GameRepository {

    override fun registerPlayer(roomCode: String, yourName: String): Flow<String> {
        val tempUserId = UUID.randomUUID().toString()
        return realtimeDatabaseUtility.setRealtimeValue(
            reference = "$LOBBY_PATH/$roomCode/players/$tempUserId",
            value = PlayerData(
                name = yourName,
                registeredTime = System.currentTimeMillis(),
                tempUserId = tempUserId
            )
        )
            .map { tempUserId }
            .flowOn(dispatcher)
    }

    override fun streamLobbySession(roomCode: String): Flow<GameLobbyData> {
        return realtimeDatabaseUtility.getRealtimeValue(
            reference = "$LOBBY_PATH/$roomCode",
            type = GameLobbyData::class.java
        )
            .flowOn(dispatcher)
    }

    override fun leaveRoom(roomCode: String, tempUserId: String): Flow<Unit> {
        return realtimeDatabaseUtility.removeRealtimeValue(
            reference = "$LOBBY_PATH/$roomCode/players/$tempUserId"
        )
            .flowOn(dispatcher)
    }

    companion object {
        private const val LOBBY_PATH = "lobby"
    }
}