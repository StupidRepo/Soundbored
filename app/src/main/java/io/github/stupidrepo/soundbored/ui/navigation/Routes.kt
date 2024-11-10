package io.github.stupidrepo.soundbored.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Audiotrack
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.Audiotrack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.graphics.vector.ImageVector

// Code stolen from:
// https://github.com/polodarb/GMS-Flags/blob/master/app/src/main/java/ua/polodarb/gmsflags/ui/navigation/AppNavigation.kt#L61-L89
// Thanks, polodarb!
sealed class Routes(var route: String) {
    data object Welcome : Routes("welcome")
    data object Main : Routes("main")
    data object Settings: Routes("settings")

    internal sealed class Nav(route: String) : Routes(route) {
        data object Soundboard : Routes("soundboard")
        data object Search : Routes("search")
        data object Favourites : Routes("fav")
    }
}

sealed class NavBarItem(
    val title: String,
    val iconActive: ImageVector,
    val iconInactive: ImageVector,
    val route: Routes
) {
    data object Soundboard : NavBarItem(
        title = "Soundboard",
        iconActive = Icons.Rounded.Audiotrack,
        iconInactive = Icons.Outlined.Audiotrack,
        route = Routes.Nav.Soundboard
    )

    data object Search : NavBarItem(
        title = "Search",
        iconActive = Icons.Rounded.Search,
        iconInactive = Icons.Outlined.Search,
        route = Routes.Nav.Search
    )

    data object Favourites : NavBarItem(
        title = "Favourites",
        iconActive = Icons.Rounded.Favorite,
        iconInactive = Icons.Outlined.Favorite,
        route = Routes.Nav.Favourites
    )
}

val navBarItems = listOf(NavBarItem.Soundboard, NavBarItem.Search, NavBarItem.Favourites)