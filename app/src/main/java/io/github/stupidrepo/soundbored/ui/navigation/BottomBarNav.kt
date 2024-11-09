package io.github.stupidrepo.soundbored.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.github.stupidrepo.soundbored.ui.screens.main.FavouritesScreen
import io.github.stupidrepo.soundbored.ui.screens.main.SearchScreen
import io.github.stupidrepo.soundbored.ui.screens.main.SoundboardScreen

@Composable
fun BottomBarNav(
    navHostController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navHostController,
        Routes.Nav.Soundboard.route,
        modifier
    ) {
        soundboardComposable(navHostController)
        searchComposable(navHostController)
        favouritesComposable(navHostController)
    }
}

private fun NavGraphBuilder.soundboardComposable(
    navHostController: NavHostController
) {
    composable(route = Routes.Nav.Soundboard.route) {
        SoundboardScreen()
    }
}

private fun NavGraphBuilder.searchComposable(
    navHostController: NavHostController
) {
    composable(route = Routes.Nav.Search.route) {
        SearchScreen()
    }
}

private fun NavGraphBuilder.favouritesComposable(
    navHostController: NavHostController
) {
    composable(route = Routes.Nav.Favourites.route) {
        FavouritesScreen()
    }
}