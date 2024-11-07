package stupidrepo.fridamanager.data

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import stupidrepo.fridamanager.FridaManagerApplication

class PrefMan(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("fridamanager", Context.MODE_PRIVATE)

    private val _scripts = mutableStateListOf<Script>()
    val scripts: SnapshotStateList<Script> get() = _scripts

    init {
        loadScripts()
    }

    private fun loadScripts() {
        val scriptsJson = sharedPreferences.getString("scripts", "[]")
        val loadedScripts: List<Script> = Json.decodeFromString(scriptsJson ?: "[]")

        _scripts.addAll(loadedScripts)
    }

    private fun saveScripts() {
        val scriptsJson = Json.encodeToString(_scripts.toList())
        sharedPreferences.edit().putString("scripts", scriptsJson).apply()
    }

    fun addScript(script: Script) {
        _scripts.add(script)
        saveScripts()
    }

    fun removeScript(script: Script) {
        _scripts.remove(script)
        saveScripts()
    }

    fun updateScriptEnabledState(script: Script, enabled: Boolean) {
        val index = _scripts.indexOf(script)
        if (index != -1) {
            _scripts[index] = script.copy(enabled = enabled)
            saveScripts()
        }
    }
}

val Context.prefMan: PrefMan
    get() {
        return (applicationContext as FridaManagerApplication).prefMan
    }