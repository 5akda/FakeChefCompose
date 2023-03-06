package iam5akda.fakechef_compose.home.view.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import iam5akda.fakechef_compose.design_system.component.GiantLoadingLayout
import iam5akda.fakechef_compose.design_system.theme.FakeChefTheme
import iam5akda.fakechef_compose.design_system.util.ComposeTools
import iam5akda.fakechef_compose.home.model.GameHistory

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun HistoryScreen(
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val historyUiState by viewModel.historyUiStateFlow.collectAsStateWithLifecycle()

    HistoryLayoutContent(
        historyUiState = historyUiState
    )
}

@Composable
private fun HistoryLayoutContent(historyUiState: HistoryUiState) {
    FakeChefTheme {
        Surface(modifier = Modifier
            .fillMaxSize()
        ) {
            when (historyUiState) {
                is HistoryUiState.Success -> {
                    HistorySuccessLayout(
                        modifier = Modifier
                            .fillMaxSize(),
                        historyList = historyUiState.historyList
                    )
                }
                is HistoryUiState.Loading -> {
                    GiantLoadingLayout(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.background)
                    )
                }
            }
        }
    }
}

@Composable
private fun HistorySuccessLayout(modifier: Modifier, historyList: List<GameHistory>) {
    LazyColumn(modifier = modifier) {
        items(historyList) { item ->
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 6.dp,
                    start = 6.dp,
                    end = 6.dp
                )
            ) {
                Text(modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                    text = item.dish,
                    fontSize = 24.sp
                )
            }
        }
    }
}

@Preview(
    uiMode = ComposeTools.PREVIEW_IN_NIGHT,
    showBackground = true,
    device = Devices.PIXEL_3
)
@Composable
private fun HistoryScreenPreview() {
    HistoryLayoutContent(
        historyUiState = HistoryUiState.Success(
            listOf(GameHistory("item 1"), GameHistory("item 2"))
        )
    )
}