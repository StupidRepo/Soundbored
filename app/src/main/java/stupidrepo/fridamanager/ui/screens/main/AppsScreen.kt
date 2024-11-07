package stupidrepo.fridamanager.ui.screens.main

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import stupidrepo.fridamanager.ui.components.AppCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppsScreen() {
    val ctx = LocalContext.current
    val pm = ctx.packageManager

    var packages by remember { mutableStateOf(pm.getInstalledApplications(PackageManager.GET_META_DATA).filter { pkg -> pkg.category == ApplicationInfo.CATEGORY_GAME }) }
    var isRefreshing by remember { mutableStateOf(false) }

    val state = rememberPullToRefreshState()

    PullToRefreshBox(isRefreshing = isRefreshing, onRefresh = {
        isRefreshing = true

        CoroutineScope(Dispatchers.IO).launch {
            pm.getInstalledApplications(PackageManager.GET_META_DATA).filter { pkg -> pkg.category == ApplicationInfo.CATEGORY_GAME }.let {
                packages = it
                isRefreshing = false
            }
        }
    }, state = state) {
        LazyColumn(
            contentPadding = PaddingValues(4.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(packages) { _, pkg ->
                AppCard(pkg)
            }
        }
    }
}