package iam5akda.fakechef_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import iam5akda.fakechef_compose.game.GameActivity
import iam5akda.fakechef_compose.home.HomeNavigation

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeNavigation(
                startGameActivity = ::startGameActivity
            )
        }
    }

    private fun startGameActivity() {
        GameActivity.navigate(this)
    }
}