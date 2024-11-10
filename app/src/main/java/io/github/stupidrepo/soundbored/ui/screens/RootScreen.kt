package io.github.stupidrepo.soundbored.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import io.github.stupidrepo.soundbored.handlers.downloading
import io.github.stupidrepo.soundbored.ui.components.BottomBar
import io.github.stupidrepo.soundbored.ui.components.TopBar
import io.github.stupidrepo.soundbored.ui.navigation.BottomBarNav

@Composable
fun RootScreen(
    topMostRootNavHostController: NavHostController,
    navHostController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = { BottomBar(navHostController = navHostController) },
        topBar = { TopBar(topMostRootNavHostController, navHostController) }
//        floatingActionButton = { FABulous(childNavController = childNavController, parentNavController = parentNavController) }
    ) { padding ->
        BottomBarNav(
            navHostController = navHostController,
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AnimatedVisibility(
                visible = downloading.isNotEmpty(),
                enter = fadeIn() + slideInVertically { it },
                exit = fadeOut() + slideOutVertically { it },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Spacer(
                        modifier = Modifier
                            .width(8.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text("${1.coerceAtLeast(downloading.count())} sound${if (downloading.count() > 1) "s" else ""} downloading...")
                }
            }
        }
    }
}