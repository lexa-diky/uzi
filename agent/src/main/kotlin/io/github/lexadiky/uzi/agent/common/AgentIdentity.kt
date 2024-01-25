package io.github.lexadiky.uzi.agent.common

import java.util.UUID

data class AgentIdentity(val instanceId: UUID) {

    companion object {

        fun create(): AgentIdentity = AgentIdentity(
            instanceId = UUID.randomUUID()
        )
    }
}