package iam5akda.fakechef_compose.game.view.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import iam5akda.fakechef_compose.design_system.theme.FakeChefTheme
import iam5akda.fakechef_compose.design_system.util.ComposeTools

@Composable
internal fun ExitLobbyDialog(
    onDismiss: () -> Unit,
    onClickYes: () -> Unit
) {
    AlertDialog(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth(),
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = onClickYes) {
                Text(text = "Yes")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "No")
            }
        },
        title = {
            Text(text = "Exit Lobby", fontWeight = FontWeight.Bold)
        },
        text = {
            Text(text = "Do you want to exit?")
        }
    )
}

@Preview(
    uiMode = ComposeTools.PREVIEW_IN_NIGHT,
    showBackground = true,
    device = Devices.PIXEL_3
)
@Composable
private fun ExitLobbyDialogPreview() {
    FakeChefTheme {
        ExitLobbyDialog({}, {})
    }
}