package iam5akda.fakechef_compose.game.view.lobby

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import iam5akda.fakechef_compose.common.extension.uriDecode
import iam5akda.fakechef_compose.game.repository.GameRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LobbyViewModel @Inject constructor(
    private val repository: GameRepository,
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

    private val _exitLobbyState = MutableSharedFlow<Boolean>()
    val exitLobbyState: SharedFlow<Boolean> = _exitLobbyState

    fun leaveRoom() {
        viewModelScope.launch {
            repository.leaveRoom(roomCode = roomCode, tempUserId = tempUserId)
                .collect {
                    _exitLobbyState.emit(true)
                }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun flowLobbySession(): Flow<LobbyUiState> {
        return repository.registerPlayer(roomCode, yourName)
            .flatMapLatest {
                tempUserId = it
                repository.streamLobbySession(roomCode)
            }
            .map {
                LobbyUiState.Success(it, roomCode)
            }
            .catch { throwable ->
                throwable.printStackTrace()
            }
    }
}