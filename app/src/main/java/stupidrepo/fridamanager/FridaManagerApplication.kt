package stupidrepo.fridamanager

import android.app.Application
import stupidrepo.fridamanager.data.PrefMan

class FridaManagerApplication: Application() {
    val prefMan by lazy { PrefMan(this) }
}