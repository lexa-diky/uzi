package io.github.lexadiky.uzi.agent.server.session

import io.github.lexadiky.uzi.agent.common.AgentIdentity
import io.github.lexadiky.uzi.agent.contract.session.event.SessionEvent
import io.github.lexadiky.uzi.agent.contract.session.event.SessionInitializationRequest
import io.github.lexadiky.uzi.agent.di.di
import io.ktor.server.websocket.DefaultWebSocketServerSession
import io.ktor.server.websocket.application
import io.ktor.websocket.Frame
import kotlinx.coroutines.Job
import java.util.UUID

class WebsocketSessionController(session: DefaultWebSocketServerSession): AutoCloseable {
    private val json = session.application.di.json
    private val sessionProcessor = session.application.di.sessionProcessor
    private val identity = AgentIdentity.create()
    private val sessionUuid = UUID.fromString(session.call.parameters["uuid"])
    private val jobs: MutableList<Job> = ArrayList()

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
        jobs += when(event) {
            is SessionInitializationRequest -> sessionProcessor.enqueue(sessionUuid, event.task)
        }
    }

    override fun close() {
        jobs.forEach { it.cancel() }
    }
}