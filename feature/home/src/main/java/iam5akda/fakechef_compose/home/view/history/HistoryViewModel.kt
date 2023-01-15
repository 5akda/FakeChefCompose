package iam5akda.fakechef_compose.home.view.history

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import iam5akda.fakechef_compose.home.repository.HomeRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: HomeRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val argMessage: String? = savedStateHandle[HistoryRoute.ARG_MESSAGE]

    val historyUiStateFlow: StateFlow<HistoryUiState> = flowHistoryList().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = HistoryUiState.Loading
    )

    private fun flowHistoryList(): Flow<HistoryUiState> {
        return repository.getHistoryList()
            .map {
                HistoryUiState.Success(it)
            }
    }
}