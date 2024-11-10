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
sealed class Routes(var route: String, var description: String) {
    data object Welcome : Routes("welcome", "Welcome!")
    data object Main : Routes("main", "Main page.")
    data object Settings: Routes("settings", "Settings.")

    internal sealed class Nav(route: String, description: String) : Routes(route, description) {
        data object Soundboard : Routes("soundboard", "View popular sounds here.")
        data object Search : Routes("search", "Search for sounds.")
        data object Favourites : Routes("fav", "View your favourite sounds. " +
                "If you have played these sounds before, you can play them offline!")
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

fun getRouteDescription(route: String?): String {
    return when (route) {
        Routes.Nav.Soundboard.route -> Routes.Nav.Soundboard.description
        Routes.Nav.Search.route -> Routes.Nav.Search.description
        Routes.Nav.Favourites.route -> Routes.Nav.Favourites.description
        else -> ""
    }
}