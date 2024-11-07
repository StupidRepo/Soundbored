package stupidrepo.fridamanager.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import stupidrepo.fridamanager.ui.navigation.navBarItems

@Composable
fun BottomBar(
    childNavController: NavHostController
) {
    val currentBackStackEntry by childNavController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    NavigationBar {
        navBarItems.forEach {
            NavigationBarItem(selected = it.route.route == currentRoute, onClick = {
                if(it.route.route != currentRoute) childNavController.navigate(it.route.route)
            }, icon = {
                Icon(if(it.route.route == currentRoute) it.iconActive else it.iconInactive, it.title)
            }, label = {
                Text(it.title)
            })
        }
    }
}