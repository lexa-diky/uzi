package io.github.lexadiky.uzi.agent.server.introspection

import io.github.lexadiky.uzi.agent.common.AgentIdentity
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.application.createApplicationPlugin
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

val UziIntrospectionPlugin = createApplicationPlugin("Introspection") {
    // TODO take identity from somewhere else
    val identity: AgentIdentity = AgentIdentity.create()
    application.routing {
        route("/introspection") {
            route("/healthcheck") {
                get("/liveness") {
                    call.respond(HttpStatusCode.OK, "alive: $identity")
                }
                get("/readiness") {
                    call.respond(HttpStatusCode.OK, "ready: $identity")
                }
                get("/startup") {
                    call.respond(HttpStatusCode.OK, "started: $identity")
                }
            }
        }
    }
}