package io.github.lexadiky.uzi.agent

import io.github.lexadiky.uzi.agent.server.UziAgent

fun main() {
    UziAgent(port = 81)
        .start()
}