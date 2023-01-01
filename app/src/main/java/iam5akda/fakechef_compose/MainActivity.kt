package iam5akda.fakechef_compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
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
        Toast.makeText(this, "I'm currently working on it.", Toast.LENGTH_SHORT).show()
    }
}