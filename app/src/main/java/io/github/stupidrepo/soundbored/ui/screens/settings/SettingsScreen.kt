package io.github.stupidrepo.soundbored.ui.screens.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import io.github.stupidrepo.soundbored.currentProvider
import io.github.stupidrepo.soundbored.providers.Providers
import io.github.stupidrepo.soundbored.ui.components.ProviderCard
import io.github.stupidrepo.soundbored.ui.components.SettingsTopBar

@Composable
fun SettingsScreen(
    topMostRootNavHostController: NavHostController,
    navHostController: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = { SettingsTopBar(topMostRootNavHostController) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyColumn {
                itemsIndexed(Providers.providers) { _, provider ->
                    ProviderCard(provider = provider, isChecked = provider == currentProvider.value) {
                        currentProvider.value = provider
                    }
                }
            }
        }
    }
}