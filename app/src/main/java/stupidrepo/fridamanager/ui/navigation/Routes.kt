package stupidrepo.fridamanager.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Android
import androidx.compose.material.icons.outlined.Apps
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material.icons.rounded.Android
import androidx.compose.material.icons.rounded.Apps
import androidx.compose.material.icons.rounded.Code
import androidx.compose.ui.graphics.vector.ImageVector

// Code stolen from:
// https://github.com/polodarb/GMS-Flags/blob/master/app/src/main/java/ua/polodarb/gmsflags/ui/navigation/AppNavigation.kt#L61-L89
// Thanks, polodarb!
sealed class Routes(var route: String) {
    data object Welcome : Routes("welcome")
    data object Main : Routes("main")

    internal sealed class Nav(route: String) : Routes(route) {
        data object Scripts : Routes("scripts")
        data object Apps : Routes("apps")
        data object Frida : Routes("frida")
    }
}

sealed class NavBarItem(
    val title: String,
    val iconActive: ImageVector,
    val iconInactive: ImageVector,
    val route: Routes
) {
    data object Scripts : NavBarItem(
        title = "Scripts",
        iconActive = Icons.Rounded.Code,
        iconInactive = Icons.Outlined.Code,
        route = Routes.Nav.Scripts
    )

    data object Apps : NavBarItem(
        title = "Apps",
        iconActive = Icons.Rounded.Apps,
        iconInactive = Icons.Outlined.Apps,
        route = Routes.Nav.Apps
    )

    data object Frida : NavBarItem(
        title = "Frida",
        iconActive = Icons.Rounded.Android,
        iconInactive = Icons.Outlined.Android,
        route = Routes.Nav.Frida
    )
}

val navBarItems = listOf(NavBarItem.Scripts, NavBarItem.Apps, NavBarItem.Frida)