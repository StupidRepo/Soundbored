package io.github.stupidrepo.soundbored.providers

import io.github.stupidrepo.soundbored.providers.myinstants.MIProvider
import io.github.stupidrepo.soundbored.providers.sbw.SBWProvider

object Providers {
    val providers = listOf(
        SBWProvider(),
        MIProvider()
    )
}