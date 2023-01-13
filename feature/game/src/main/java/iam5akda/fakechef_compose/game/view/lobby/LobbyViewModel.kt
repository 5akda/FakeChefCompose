package iam5akda.fakechef_compose.game.view.lobby

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import iam5akda.fakechef_compose.common.extension.uriDecode
import iam5akda.fakechef_compose.game.repository.GameRepository
import javax.inject.Inject

@HiltViewModel
class LobbyViewModel @Inject constructor(
    private val repository: GameRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val roomCode: String by lazy {
        val argRoomCode: String? = savedStateHandle["roomCode"]
        argRoomCode.uriDecode()
    }

    val yourName: String by lazy {
        val argYourName: String? = savedStateHandle["yourName"]
        argYourName.uriDecode()
    }
}