package io.github.stupidrepo.soundbored

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import io.github.stupidrepo.soundbored.ui.navigation.RootAppNavigation
import io.github.stupidrepo.soundbored.ui.theme.AndroidAnnoyance

class MainActivity : ComponentActivity() {
//    private var setupComplete
//        get() = run { getPreferences(MODE_PRIVATE).getBoolean("setupComplete", false) }
//        set(value) {
//            getPreferences(MODE_PRIVATE).edit().putBoolean("setupComplete", value).apply()
//        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AndroidAnnoyance {
                val navHostController = rememberNavController()
                RootAppNavigation(
                    navHostController = navHostController,
//                    isSetupComplete = setupComplete,
//                    onSetupComplete = { setupComplete = true },
                )
            }
        }
    }
}