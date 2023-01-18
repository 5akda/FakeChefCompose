package iam5akda.fakechef_compose.game.view.lobby

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import iam5akda.fakechef_compose.common.extension.uriDecode
import iam5akda.fakechef_compose.game.model.GameLobbyData
import iam5akda.fakechef_compose.game.model.LobbySessionStatus
import iam5akda.fakechef_compose.game.repository.GameRepository
import iam5akda.fakechef_compose.game.repository.HostRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class LobbyViewModel @Inject constructor(
    private val gameRepository: GameRepository,
    private val hostRepository: HostRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var tempUserId: String = ""

    private val roomCode: String by lazy {
        val argRoomCode: String? = savedStateHandle[LobbyRoute.ARG_ROOM_CODE]
        argRoomCode.uriDecode()
    }

    private val yourName: String by lazy {
        val argYourName: String? = savedStateHandle[LobbyRoute.ARG_YOUR_NAME]
        argYourName.uriDecode()
    }

    val lobbyUiState: StateFlow<LobbyUiState> = flowLobbySession().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = LobbyUiState.Loading
    )

    fun leaveRoom() {
        gameRepository.leaveRoom(roomCode = roomCode, tempUserId = tempUserId)
            .launchIn(viewModelScope)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun hostStartGame() {
        val mutablePlayerList = (lobbyUiState.value as LobbyUiState.Idle)
            .lobbyData.getPlayerList().toMutableList()

        mutablePlayerList.shuffle()

        hostRepository.assignHeadChef(roomCode, mutablePlayerList.removeFirst().tempUserId)
            .flatMapLatest {
                hostRepository.assignFakeChef(roomCode, mutablePlayerList.removeFirst().tempUserId)
            }
            .flatMapLatest {
                hostRepository.setLobbyStatus(roomCode, LobbySessionStatus.ORDERING)
            }
            .launchIn(viewModelScope)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun flowLobbySession(): Flow<LobbyUiState> {
        return gameRepository.registerPlayer(roomCode, yourName)
            .flatMapLatest {
                tempUserId = it
                gameRepository.streamLobbySession(roomCode)
            }
            .map {
                processLobbySession(it)
            }
            .catch {
                emit(LobbyUiState.Kicked)
            }
    }

    private fun processLobbySession(data: GameLobbyData): LobbyUiState {
        return when (data.status) {
            LobbySessionStatus.IDLE.name -> {
                val thisPlayer = data.getPlayerList().find { it.tempUserId == tempUserId }
                if (thisPlayer != null) {
                    LobbyUiState.Idle(data, roomCode, tempUserId)
                } else {
                    LobbyUiState.Kicked
                }
            }
            LobbySessionStatus.ORDERING.name -> {
                LobbyUiState.Ordering(roomCode, tempUserId)
            }
            else -> {
                throw RuntimeException()
            }
        }
    }
}