package iam5akda.fakechef_compose.home.view.history

import android.os.Bundle
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import iam5akda.fakechef_compose.design_system.theme.FakeChefTheme

@Composable
internal fun HistoryScreeen(
    viewModel: HistoryViewModel = hiltViewModel(),
    argsBundle: Bundle?
) {
    HistoryLayoutContent(argsBundle?.getString("message") ?: "")
}

@Composable
private fun HistoryLayoutContent(message: String) {
    FakeChefTheme {
        Surface {
            Text(text = message)
        }
    }
}