package iam5akda.fakechef_compose.home.repository

import iam5akda.fakechef_compose.common.dispatcher.IoDispatcher
import iam5akda.fakechef_compose.home.model.GameHistory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HomeAnimationSource @Inject constructor(
    @IoDispatcher
    private val dispatcher: CoroutineDispatcher
) : HomeRepository{

    override fun getAnimatedString(repeat: Int): Flow<String> {
        val frame = listOf(
            "Fake Chef  ",
            "ake Chef  F",
            "ke Chef  Fa",
            "e Chef  Fak",
            " Chef  Fake",
            "Chef  Fake ",
            "hef  Fake C",
            "ef  Fake Ch",
            "f  Fake Che",
            "  Fake Chef",
            " Fake Chef ",
        )
        return flow {
            repeat(repeat) {
                frame.forEach {
                    delay(FRAME_DELAY)
                    emit(it)
                }
            }
        }
            .flowOn(dispatcher)
    }

    override fun getHistoryList(): Flow<List<GameHistory>> {
        val mockData = listOf(
            GameHistory("ยัง"),
            GameHistory("ทำ"),
            GameHistory("ไม่"),
            GameHistory("เสร็จ")
        )
        return flow {
            delay(MOCK_LOAD_DELAY)
            emit(mockData)
        }
            .flowOn(dispatcher)
    }

    companion object {
        private const val FRAME_DELAY = 100L
        private const val MOCK_LOAD_DELAY = 1_000L
    }
}