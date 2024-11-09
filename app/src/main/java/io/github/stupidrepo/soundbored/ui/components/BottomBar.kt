package io.github.stupidrepo.soundbored.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
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