package io.github.lexadiky.uzi.agent.server.session

import io.github.lexadiky.uzi.agent.contract.session.event.SessionEvent
import io.github.lexadiky.uzi.agent.contract.session.event.SessionInitializationRequest
import io.github.lexadiky.uzi.agent.di.di
import io.ktor.server.websocket.DefaultWebSocketServerSession
import io.ktor.server.websocket.application
import io.ktor.websocket.Frame

class WebsocketSessionController(session: DefaultWebSocketServerSession) {
    private val json = session.application.di.json
    private val sessionProcessor = session.application.di.sessionProcessor

    suspend fun onNextFrame(frame: Frame) {
        val isDataFrame = frame is Frame.Binary || frame is Frame.Text
        if (isDataFrame) {
            val bodyAsText = frame.data.toString(Charsets.UTF_8)
            val event = json.decodeFromString<SessionEvent>(bodyAsText)
            onEvent(event)
        } else {
            // TODO what to do?
            error("can't handle")
        }
    }

    private suspend fun onEvent(event: SessionEvent) {
        when(event) {
            is SessionInitializationRequest -> sessionProcessor.enqueue(event.task)
        }
    }
}