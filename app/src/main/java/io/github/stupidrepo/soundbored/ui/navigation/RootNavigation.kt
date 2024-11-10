package io.github.stupidrepo.soundbored.ui.navigation

import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.github.stupidrepo.soundbored.ui.screens.RootScreen
import io.github.stupidrepo.soundbored.ui.screens.settings.SettingsScreen

@Composable
internal fun RootAppNavigation(
    modifier: Modifier = Modifier,
    topMostRootNavHostController: NavHostController,
//    isSetupComplete: Boolean,
//    onSetupComplete: () -> Unit
) {
    NavHost(
        navController = topMostRootNavHostController,
        startDestination = Routes.Main.route, // if (!isSetupComplete) Routes.Welcome.route else Routes.Main.route,
        modifier = modifier,
        enterTransition = { slideInVertically { it } },
        exitTransition = { slideOutVertically { it } }
    ) {
//        welcomeComposable(
//            navController = navHostController,
//            onSetupComplete = onSetupComplete
//        )
        mainComposable(
            topMostRootNavHostController = topMostRootNavHostController
        )
        settingsComposable(
            topMostRootNavHostController = topMostRootNavHostController
        )
    }
}
//
//private fun NavGraphBuilder.welcomeComposable(
//    navController: NavHostController,
//    onSetupComplete: () -> Unit
//) {
//    composable(
//        route = Routes.Welcome.route
//    ) {
//        val context = LocalContext.current
//        val isLoading = remember {
//            mutableStateOf(false)
//        }
//
//        Scaffold { padding ->
//            WelcomeScreen(isLoading = isLoading, modifier = Modifier.padding(padding)) {
//                isLoading.value = true
//                CoroutineScope(Dispatchers.IO).launch {
//                    if (Shell.getShell().isRoot) {
//                        withContext(Dispatchers.Main) {
//                            delay(700)
//                            onSetupComplete()
//                            navController.navigate(Routes.Main.route)
//                        }
//                    } else {
//                        withContext(Dispatchers.Main) {
//                            Toast.makeText(context, "You denied me root :(", Toast.LENGTH_SHORT)
//                                .show()
//                        }
//                    }
//
//                    isLoading.value = false
//                }
//            }
//        }
//    }
//}

private fun NavGraphBuilder.mainComposable(
    topMostRootNavHostController: NavHostController
) {
    composable(
        route = Routes.Main.route
    ) {
        RootScreen(topMostRootNavHostController)
    }
}

private fun NavGraphBuilder.settingsComposable(
    topMostRootNavHostController: NavHostController
) {
    composable(
        route = Routes.Settings.route
    ) {
        SettingsScreen(topMostRootNavHostController)
    }
}