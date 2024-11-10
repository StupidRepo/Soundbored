package io.github.stupidrepo.soundbored.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import io.github.stupidrepo.soundbored.ui.navigation.Routes
import io.github.stupidrepo.soundbored.ui.navigation.getRouteDescription
import io.github.stupidrepo.soundbored.ui.navigation.navBarItems

@Composable
fun BottomBar(
    navHostController: NavHostController
) {
    val currentBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    NavigationBar {
        navBarItems.forEach {
            NavigationBarItem(selected = it.route.route == currentRoute, onClick = {
                if(it.route.route != currentRoute) navHostController.navigate(it.route.route)
            }, icon = {
                Icon(if(it.route.route == currentRoute) it.iconActive else it.iconInactive, it.title)
            }, label = {
                Text(it.title)
            })
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBar(
    topMostRootNavHostController: NavHostController,
    navHostController: NavHostController
) {
    TopAppBar(title = {
        val currentBackStackEntry by navHostController.currentBackStackEntryAsState()
        val currentRoute = currentBackStackEntry?.destination?.route
        Column {
            Text(
                "Soundbored",
                style = typography.titleMedium
            )
            Text(
                getRouteDescription(currentRoute),
                style = typography.bodySmall
            )
        }
    }, actions = {
        IconButton(onClick = {
            topMostRootNavHostController.navigate(Routes.Settings.route)
        }) {
            Icon(Icons.Rounded.Settings, "Settings")
        }
    })
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SettingsTopBar(
    topMostRootNavHostController: NavHostController
) {
    TopAppBar(title = {
        Text("Settings")
    }, navigationIcon = {
        IconButton(onClick = { topMostRootNavHostController.popBackStack() }) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                contentDescription = "Back Icon"
            )
        }
    })
}