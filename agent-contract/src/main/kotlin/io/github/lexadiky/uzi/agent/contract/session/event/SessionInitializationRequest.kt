package io.github.lexadiky.uzi.agent.contract.session.event

import io.github.lexadiky.uzi.engine.task.UziTask
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.net.URLClassLoader

@[Serializable SerialName("init")]
data class SessionInitializationRequest(
    @SerialName("task")
    val task: UziTask
) : SessionEvent
