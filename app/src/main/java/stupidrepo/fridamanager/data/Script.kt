package stupidrepo.fridamanager.data

import android.net.Uri
import kotlinx.serialization.Serializable
import java.io.File

@Serializable
data class Script(
    val uri: String,
    var enabled: Boolean
) {
    private fun parseUri(): Uri {
        return Uri.parse(uri)
    }

    fun getFile(): File? {
        parseUri().path?.let {
            return File(it)
        }

        return null
    }
}
