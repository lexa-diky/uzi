package io.github.lexadiky.uzi.agent.server.session

import io.ktor.server.application.createApplicationPlugin
import io.ktor.server.routing.routing
import io.ktor.server.websocket.webSocket
import io.ktor.websocket.Frame
import kotlinx.coroutines.isActive

val UziSessionPlugin = createApplicationPlugin("UziSession") {
    application.routing {
        webSocket("/session/{uuid}") {
            val controller = WebsocketSessionController(this)

            while (this.isActive && !incoming.isClosedForReceive) {
                val nextMessage = incoming.tryReceive()
                when {
                    nextMessage.isSuccess -> controller.onNextFrame(nextMessage.getOrThrow())
                    nextMessage.isFailure -> Unit
                    nextMessage.isClosed -> {
                        controller.close()
                        break
                    }
                }
            }
        }
    }
}
