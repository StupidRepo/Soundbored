package io.github.stupidrepo.soundbored.ui.screens.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import io.github.stupidrepo.soundbored.currentProvider
import io.github.stupidrepo.soundbored.providers.Providers
import io.github.stupidrepo.soundbored.ui.components.BodyTitleText
import io.github.stupidrepo.soundbored.ui.components.BottomBar
import io.github.stupidrepo.soundbored.ui.components.SettingsTopBar

@Composable
fun SettingsScreen(
    topMostRootNavHostController: NavHostController,
    navHostController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = { BottomBar(navHostController = navHostController) },
        topBar = { SettingsTopBar(topMostRootNavHostController) }
//        floatingActionButton = { FABulous(childNavController = childNavController, parentNavController = parentNavController) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column {
                BodyTitleText(text = "Current Provider: ${currentProvider.value.getProviderName()}")
                Providers.providers.forEach {
                    Row {
                        Text(it.getProviderName())

                        Spacer(modifier = Modifier.width(8.dp))

                        Button(onClick = {
                            currentProvider.value = it
                        }) {
                            Text("Select")
                        }
                    }
                }
            }
        }
    }
}