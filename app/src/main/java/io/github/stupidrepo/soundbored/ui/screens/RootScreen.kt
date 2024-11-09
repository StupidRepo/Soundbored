package io.github.stupidrepo.soundbored.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import io.github.stupidrepo.soundbored.ui.components.BottomBar
import io.github.stupidrepo.soundbored.ui.navigation.BottomBarNav

@Composable
fun RootScreen(
    navHostController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = { BottomBar(navHostController = navHostController) },
//        floatingActionButton = { FABulous(childNavController = childNavController, parentNavController = parentNavController) }
    ) { padding ->
        BottomBarNav(
            navHostController = navHostController,
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
        )
    }
}