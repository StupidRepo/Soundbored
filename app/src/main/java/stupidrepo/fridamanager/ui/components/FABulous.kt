package stupidrepo.fridamanager.ui.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import stupidrepo.fridamanager.data.Script
import stupidrepo.fridamanager.data.prefMan
import stupidrepo.fridamanager.ui.navigation.Routes

@Composable
fun FABulous(
    childNavController: NavHostController
) {
    val current by childNavController.currentBackStackEntryAsState()
    val ctx = LocalContext.current
    val prefMan = ctx.prefMan

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents()
    ) { uris: List<Uri> ->
        uris.forEach { uri ->
            prefMan.addScript(Script(uri.toString(), false))
        }
    }

    if(current?.destination?.route == Routes.Nav.Scripts.route)
        FloatingActionButton(onClick = {
            filePickerLauncher.launch("application/javascript")
        }) {
            Icon(Icons.Rounded.Add, "Add Script")
        }
}