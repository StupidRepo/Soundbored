package stupidrepo.fridamanager.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import stupidrepo.fridamanager.ui.screens.main.AppsScreen
import stupidrepo.fridamanager.ui.screens.main.FridaScreen
import stupidrepo.fridamanager.ui.screens.main.ScriptsScreen

@Composable
fun BottomBarNav(
    childNavController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = childNavController,
        startDestination = Routes.Nav.Scripts.route,
        modifier = modifier
    ) {
        scriptsComposable(navController = childNavController)
        appsComposable(navController = childNavController)
        fridaComposable(navController = childNavController)
    }
}

private fun NavGraphBuilder.scriptsComposable(
    navController: NavHostController
) {
    composable(route = Routes.Nav.Scripts.route) {
        ScriptsScreen(navController)
    }
}

private fun NavGraphBuilder.appsComposable(
    navController: NavHostController
) {
    composable(route = Routes.Nav.Apps.route) {
        AppsScreen()
    }
}

private fun NavGraphBuilder.fridaComposable(
    navController: NavHostController
) {
    composable(route = Routes.Nav.Frida.route) {
        FridaScreen()
    }
}