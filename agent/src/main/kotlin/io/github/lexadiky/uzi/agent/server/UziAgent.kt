package io.github.lexadiky.uzi.agent.server

import io.github.lexadiky.uzi.agent.server.introspection.UziIntrospectionPlugin
import io.github.lexadiky.uzi.agent.server.session.UziSessionPlugin
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.websocket.WebSockets

class UziAgent(
    private val port: Int = 80,
    private val host: String = "0.0.0.0"
) {
    fun start() {
        embeddedServer(Netty, port = port, host = host) {
            install(WebSockets)
            install(ContentNegotiation) {
                json()
            }

            install(UziIntrospectionPlugin)
            install(UziSessionPlugin)
        }.start(wait = true)
    }
}