package iam5akda.fakechef_compose.game.repository

import iam5akda.fakechef_compose.common.dispatcher.IoDispatcher
import iam5akda.fakechef_compose.data.realtime.RealtimeDatabaseUtility
import iam5akda.fakechef_compose.game.model.LobbySessionStatus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HostRealtimeSource @Inject constructor(
    private val realtimeDatabaseUtility: RealtimeDatabaseUtility,
    @IoDispatcher
    private val dispatcher: CoroutineDispatcher
) : HostRepository {

    override fun setLobbyStatus(roomCode: String, status: LobbySessionStatus): Flow<Unit> {
        return realtimeDatabaseUtility.setRealtimeValue(
            reference = "$LOBBY_PATH/$roomCode/status",
            value = status.name
        )
            .flowOn(dispatcher)
    }

    override fun assignFakeChef(roomCode: String, playerId: String): Flow<Unit> {
        return realtimeDatabaseUtility.setRealtimeValue(
            reference = "$LOBBY_PATH/$roomCode/fakeChefId",
            value = playerId
        )
            .flowOn(dispatcher)
    }

    override fun assignHeadChef(roomCode: String, playerId: String): Flow<Unit> {
        return realtimeDatabaseUtility.setRealtimeValue(
            reference = "$LOBBY_PATH/$roomCode/headChefId",
            value = playerId
        )
            .flowOn(dispatcher)
    }

    companion object {
        private const val LOBBY_PATH = "lobby"
    }
}