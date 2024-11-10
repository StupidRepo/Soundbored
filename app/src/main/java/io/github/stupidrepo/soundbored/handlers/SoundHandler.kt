package io.github.stupidrepo.soundbored.handlers

import android.content.Context
import android.media.MediaPlayer
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import io.github.stupidrepo.soundbored.retrofit.GenericSound
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.net.URL

val downloading = mutableListOf<File>()
private var mediaPlayer = mutableStateOf<MediaPlayer?>(null)

suspend fun downloadSound(url: String, fileName: String, ctx: Context): File {
    val parsedURL = URL(url)
    val file = File(ctx.cacheDir, fileName)

    downloading.add(file)

    withContext(Dispatchers.IO) {
        parsedURL.openStream().use { input ->
            FileOutputStream(file).use { output ->
                input.copyTo(output)
            }
        }
    }

    downloading.remove(file)
    return file
}

fun play(sound: GenericSound, ctx: Context) {
    mediaPlayer.value?.release()

    CoroutineScope(Dispatchers.Main).launch {
        try {
            val file = File(ctx.cacheDir, sound.fileName)
            if (!file.exists()) {
                downloadSound(sound.soundURL, sound.fileName, ctx)
            }

            mediaPlayer.value = MediaPlayer().apply {
                setDataSource(file.absolutePath)
                setOnPreparedListener { start() }
                prepareAsync()
            }
        } catch(e: Exception) {
            ctx.mainExecutor.execute {
                Toast.makeText(ctx, "Failed to play! $e", Toast.LENGTH_SHORT).show()
            }
        }
    }
}