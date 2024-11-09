package io.github.stupidrepo.soundbored.ui.navigation

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.topjohnwu.superuser.Shell
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import io.github.stupidrepo.soundbored.ui.screens.RootScreen
import io.github.stupidrepo.soundbored.ui.screens.intro.WelcomeScreen

@Composable
internal fun RootAppNavigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
//    isSetupComplete: Boolean,
//    onSetupComplete: () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Routes.Main.route, // if (!isSetupComplete) Routes.Welcome.route else Routes.Main.route,
        modifier = modifier
    ) {
//        welcomeComposable(
//            navController = navHostController,
//            onSetupComplete = onSetupComplete
//        )
        mainComposable(
            navHostController = navHostController
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
    navHostController: NavHostController
) {
    composable(
        route = Routes.Main.route
    ) {
        RootScreen()
    }
}