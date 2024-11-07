package stupidrepo.fridamanager.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import stupidrepo.fridamanager.ui.navigation.BottomBarNav
import stupidrepo.fridamanager.ui.components.BottomBar

@Composable
fun RootScreen(
    parentNavController: NavHostController,
    childNavController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = { BottomBar(childNavController = childNavController) },
//        floatingActionButton = { FABulous(childNavController = childNavController, parentNavController = parentNavController) }
    ) { padding ->
        BottomBarNav(
            childNavController = childNavController,
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        )
    }
}