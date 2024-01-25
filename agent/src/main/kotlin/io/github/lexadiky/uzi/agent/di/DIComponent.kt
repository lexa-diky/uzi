package io.github.lexadiky.uzi.agent.di

import io.github.lexadiky.uzi.agent.processor.SessionProcessor
import io.ktor.server.application.Application
import io.ktor.util.AttributeKey
import kotlinx.serialization.json.Json

class DIComponent {
    val sessionProcessor = SessionProcessor()
    val json = Json

    companion object {

        val key = AttributeKey<DIComponent>("key")
    }
}

val Application.di: DIComponent
    get() = this.attributes.computeIfAbsent(DIComponent.key, ::DIComponent)