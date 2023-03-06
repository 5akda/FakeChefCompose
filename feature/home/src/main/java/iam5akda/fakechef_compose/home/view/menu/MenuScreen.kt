package iam5akda.fakechef_compose.home.view.menu

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import iam5akda.fakechef_compose.design_system.theme.FakeChefTheme
import iam5akda.fakechef_compose.design_system.theme.TransGray
import iam5akda.fakechef_compose.design_system.util.ComposeTools
import iam5akda.fakechef_compose.home.R

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun MenuScreen(
    viewModel: MenuViewModel = hiltViewModel(),
    onClickCreateRoom: () -> Unit,
    onClickHistory: (String) -> Unit,
    onClickHelp: () -> Unit
) {
    val animatedAppName by viewModel.animationStateFlow.collectAsStateWithLifecycle()

    MenuLayoutContent(
        animatedAppName = animatedAppName,
        onClickCreateRoom = onClickCreateRoom,
        onClickHistory = onClickHistory,
        onClickHelp = onClickHelp
    )
}

@Composable
private fun MenuLayoutContent(
    animatedAppName: String,
    onClickCreateRoom: () -> Unit,
    onClickHistory: (String) -> Unit,
    onClickHelp: () -> Unit
) {
    FakeChefTheme {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        ) {
            MenuAnimatedAppName(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp),
                animatedAppName = animatedAppName
            )
            MenuFeatureSection(modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 32.dp)
                .align(Alignment.BottomCenter),
                onClickCreateRoom = onClickCreateRoom,
                onClickHistory = onClickHistory,
                onClickHelp = onClickHelp
            )
        }
    }
}

@Composable
private fun MenuAnimatedAppName(
    modifier: Modifier,
    animatedAppName: String
) {
    Text(modifier = modifier,
        text = animatedAppName,
        textAlign = TextAlign.Center,
        fontFamily = FontFamily.Cursive,
        color = MaterialTheme.colors.secondary,
        style = MaterialTheme.typography.h3,
    )
}

@Composable
private fun MenuFeatureSection(
    modifier: Modifier,
    onClickCreateRoom: () -> Unit,
    onClickHistory: (String) -> Unit,
    onClickHelp: () -> Unit
) {
    Column(modifier = modifier) {
        Button(modifier = Modifier
            .fillMaxWidth(),
            onClick = onClickCreateRoom
        ) {
            Text(modifier = Modifier
                .padding(vertical = 2.dp),
                text = stringResource(id = R.string.play),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h6
            )
        }
        Row(modifier = Modifier
            .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedButton(modifier = Modifier
                .weight(1f),
                onClick = { onClickHistory.invoke("NOT FINISH") },
                border = BorderStroke(width = 2.dp, color = MaterialTheme.colors.primary)
            ) {
                Text(modifier = Modifier
                    .padding(vertical = 2.dp),
                    text = stringResource(id = R.string.history),
                    fontWeight = FontWeight.Bold
                )
            }
            OutlinedButton(modifier = Modifier
                .weight(1f),
                onClick = onClickHelp,
                border = BorderStroke(width = 2.dp, color = MaterialTheme.colors.primary)
            ) {
                Text(modifier = Modifier
                    .padding(vertical = 2.dp),
                    text = stringResource(id = R.string.help),
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Text(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
            text = stringResource(id = R.string.credit),
            textAlign = TextAlign.Center,
            color = TransGray,
            style = MaterialTheme.typography.caption
        )
    }
}

@Preview(
    uiMode = ComposeTools.PREVIEW_IN_NIGHT,
    showBackground = true,
    device = Devices.PIXEL_3
)
@Composable
private fun HomeScreenPreview() {
    MenuLayoutContent(
        animatedAppName = "Fake Chef",
        onClickCreateRoom = {},
        onClickHistory = {},
        onClickHelp = {}
    )
}