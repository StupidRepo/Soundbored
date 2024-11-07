package stupidrepo.fridamanager.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import stupidrepo.fridamanager.data.prefMan
import stupidrepo.fridamanager.ui.components.FABulous
import stupidrepo.fridamanager.ui.components.ScriptCard

@Composable
fun ScriptsScreen(
    navController: NavHostController
) {
    val ctx = LocalContext.current
    val prefMan = ctx.prefMan

    val scripts = prefMan.scripts

    LazyColumn(
        contentPadding = PaddingValues(4.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(scripts) { _, script ->
            ScriptCard(script)
        }
    }

    Box(
        contentAlignment = Alignment.BottomEnd,
    ) {
        Box(modifier = Modifier.padding(24.dp)) {
            FABulous(navController)
        }
    }
}