package iam5akda.fakechef_compose.home.view.history

import iam5akda.fakechef_compose.home.model.GameHistory

sealed interface HistoryUiState {
    object Loading : HistoryUiState
    data class Success(val historyList: List<GameHistory> = listOf()) : HistoryUiState
}