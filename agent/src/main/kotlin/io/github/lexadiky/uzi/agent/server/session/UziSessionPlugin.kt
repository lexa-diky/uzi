package io.github.lexadiky.uzi.agent.server.session

import io.ktor.server.application.createApplicationPlugin
import io.ktor.server.routing.routing
import io.ktor.server.websocket.webSocket
import kotlinx.coroutines.isActive

val UziSessionPlugin = createApplicationPlugin("UziSession") {
    application.routing {
        webSocket("/session/{uuid}") {
            val controller = WebsocketSessionController(this)

            while (this.isActive) {
                val nextMessage = incoming.receive()
                controller.onNextFrame(nextMessage)
            }
        }
    }
}
