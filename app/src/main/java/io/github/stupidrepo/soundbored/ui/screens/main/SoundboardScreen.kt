package io.github.stupidrepo.soundbored.ui.screens.main

import android.media.MediaPlayer
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import io.github.stupidrepo.soundbored.providers.Providers
import io.github.stupidrepo.soundbored.retrofit.apis.soundbuttonsworld.Sound
import io.github.stupidrepo.soundbored.ui.components.SoundboardCard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.net.URL

var isRefreshing = mutableStateOf(false)
var sounds = mutableStateListOf<Sound>()

var provider = mutableStateOf(Providers.providers[0])

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SoundboardScreen() {
    val TAG = "SoundboardScreen-Fetch"

    val ctx = LocalContext.current
    val state = rememberPullToRefreshState()

    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }

    suspend fun downloadSound(url: String, fileName: String): File {
        val url = URL(url)
        val file = File(ctx.cacheDir, fileName)

        withContext(Dispatchers.IO) {
            url.openStream().use { input ->
                FileOutputStream(file).use { output ->
                    input.copyTo(output)
                }
            }
        }
        return file
    }

//    LaunchedEffect(true) {
//        sounds = provider.value.getSounds().toMutableStateList()
//    }

    PullToRefreshBox(isRefreshing = isRefreshing.value, onRefresh = {
        CoroutineScope(Dispatchers.IO).launch {
            sounds = provider.value.getSounds().toMutableStateList()
        }
    }, state = state) {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2)
        ) {
            itemsIndexed(sounds) { index, sound ->
                SoundboardCard(sound = sound, onExpand = {}, onPlay = {
                    Log.i(TAG, "SoundboardScreen: Playing sound ${sound.fileName}")

                    mediaPlayer?.release()
                    CoroutineScope(Dispatchers.Main).launch {
                        val file = File(ctx.cacheDir, sound.fileName)
                        if (!file.exists()) {
                            downloadSound("${provider.value.getUploadURL()}/${sound.fileName}", sound.fileName)
                        }
                        mediaPlayer = MediaPlayer().apply {
                            setDataSource(file.absolutePath)
                            setOnPreparedListener { start() }
                            prepareAsync()
                        }
                    }
                })
            }
        }
    }
}