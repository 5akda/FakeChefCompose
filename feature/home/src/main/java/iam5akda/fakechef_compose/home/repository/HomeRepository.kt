package iam5akda.fakechef_compose.home.repository

import iam5akda.fakechef_compose.home.model.GameHistory
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getAnimatedString(repeat: Int): Flow<String>
    fun getHistoryList(): Flow<List<GameHistory>>
}