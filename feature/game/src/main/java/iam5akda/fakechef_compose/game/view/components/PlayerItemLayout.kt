package iam5akda.fakechef_compose.game.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import iam5akda.fakechef_compose.design_system.theme.FakeChefTheme
import iam5akda.fakechef_compose.design_system.utils.ComposeTools
import iam5akda.fakechef_compose.game.R
import iam5akda.fakechef_compose.game.model.PlayerData

@Composable
fun PlayerItemLayout(modifier: Modifier, playerData: PlayerData) {
    Row(modifier = modifier) {
        Image(
            modifier = Modifier
                .padding(horizontal = 4.dp, vertical = 2.dp),
            painter = painterResource(id = R.drawable.ic_player_24),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground)
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 2.dp, vertical = 2.dp),
            text = playerData.name,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onBackground,
            fontSize = 18.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(
    uiMode = ComposeTools.PREVIEW_IN_NIGHT,
    showBackground = true,
    device = Devices.PIXEL_3
)
@Composable
fun PlayerItemLayoutPreview() {
    FakeChefTheme {
        PlayerItemLayout(
            modifier = Modifier
                .fillMaxWidth(),
            playerData = PlayerData("User1234HasTheLongestNameIntheWorldHahahahahaahahah")
        )
    }
}