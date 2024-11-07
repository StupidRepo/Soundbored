package stupidrepo.fridamanager.ui.components

import android.content.pm.ApplicationInfo
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import stupidrepo.fridamanager.data.Script
import stupidrepo.fridamanager.data.prefMan

@Composable
fun OurCard(content: @Composable ColumnScope.() -> Unit) {
    ElevatedCard(
        modifier = Modifier.padding(4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            content()
        }
    }
}

@Composable
fun AppCard(app: ApplicationInfo) {
    val ctx = LocalContext.current
    val pm = ctx.packageManager

    val name = pm.getApplicationLabel(app)

    OurCard {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val drawable = pm.getApplicationIcon(app)
            Image(
                drawable
                    .toBitmap(config = Bitmap.Config.ARGB_8888)
                    .asImageBitmap(),
                contentDescription = "App icon",
                modifier = Modifier
                    .size(64.dp)
                    .padding(8.dp)
            )

            Spacer(Modifier.width(8.dp))

            Column(
                Modifier.weight(2f)
            ) {
                BodyTitleText(name.toString())
                ExtraText(app.packageName ?: "Unknown")
            }

            Spacer(Modifier.width(4.dp))

            FloatingActionButton(onClick = {
                MaterialAlertDialogBuilder(ctx)
                    .setTitle("Start ${name}?")
                    .setMessage("Are you sure you want to start ${name}?")
                    .setPositiveButton("Yes") { dialog, _ ->
                        ctx.startActivity(ctx.packageManager.getLaunchIntentForPackage(app.packageName))
                        dialog.dismiss()
                    }
                    .setNegativeButton("No") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }) {
                Icon(Icons.Rounded.PlayArrow, "Play")
            }
        }
    }
}

@Composable
fun ScriptCard(script: Script) {
    val ctx = LocalContext.current
    val prefMan = ctx.prefMan

    val isEnabled by rememberUpdatedState(newValue = script.enabled)

    OurCard {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BodyTitleText((script.getFile()?.name) ?: "Unknown File", Modifier.weight(4f))

            Spacer(Modifier.width(4.dp))

            Switch(checked = isEnabled, onCheckedChange = {
                prefMan.updateScriptEnabledState(script, it)
            })

            Spacer(Modifier.width(4.dp))

            IconButton({
                prefMan.removeScript(script)
            }) {
                Icon(Icons.Rounded.Delete, "Delete script from list")
            }
        }
    }
}