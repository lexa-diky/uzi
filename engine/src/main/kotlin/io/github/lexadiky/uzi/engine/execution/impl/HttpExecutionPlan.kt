package io.github.lexadiky.uzi.engine.execution.impl

import io.github.lexadiky.uzi.engine.execution.ExecutionContext
import io.github.lexadiky.uzi.engine.execution.ExecutionPlan
import io.github.lexadiky.uzi.engine.execution.measureFact
import io.github.lexadiky.uzi.engine.execution.measureTime
import io.github.lexadiky.uzi.engine.execution.trace
import io.ktor.client.HttpClient
import io.ktor.client.request.headers
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import java.net.URI

internal class HttpExecutionPlan(
    private val uri: URI,
    private val method: String,
    private val headers: Map<String, List<String>>,
    private val client: HttpClient
) : ExecutionPlan {

    override suspend fun execute(context: ExecutionContext) = context.trace(this) {
        context.measureTime("http-request-time") {
            val response = client.request {
                method = HttpMethod(this@HttpExecutionPlan.method)
                url(this@HttpExecutionPlan.uri.toURL())
                headers {
                    this@HttpExecutionPlan.headers.forEach { (key, values) ->
                        values.forEach { value ->
                            append(key, value)
                        }
                    }
                }
            }

            context.measureFact(
                typeId = "http-request-success",
                fact = response.status.isSuccess().toString()
            )
        }
    }
}