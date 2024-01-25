package io.github.lexadiky.uzi.agent

import java.util.UUID

data class AgentIdentity(val instanceId: UUID) {

    companion object {

        fun create(): AgentIdentity = AgentIdentity(
            instanceId = UUID.randomUUID()
        )
    }
}