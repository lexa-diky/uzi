package io.github.lexadiky.uzi.agent

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

class UziAgent(
    private val port: Int = 80,
    private val host: String = "0.0.0.0"
) {
    private val identity: AgentIdentity = AgentIdentity.create()

    fun start() {
        embeddedServer(Netty, port = port, host = host) {
            routing {
                route("/introspection") {
                    route("/healthcheck") {
                        get("/liveness") {
                            call.respond(HttpStatusCode.OK, "healthy: $identity")
                        }
                    }
                }
            }
        }.start(wait = true)
    }
}