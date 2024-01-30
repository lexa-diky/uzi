package io.github.lexadiky.uzi.engine.execution.impl

import io.github.lexadiky.uzi.engine.execution.ExecutionContext
import io.github.lexadiky.uzi.engine.execution.ExecutionPlan
import io.github.lexadiky.uzi.engine.execution.logged
import io.ktor.client.HttpClient
import io.ktor.client.request.headers
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.http.HttpMethod
import java.net.URI

internal class HttpExecutionPlan(
    private val uri: URI,
    private val method: String,
    private val headers: Map<String, List<String>>,
    private val client: HttpClient
) : ExecutionPlan {

    override suspend fun execute(context: ExecutionContext) = context.logged(this) {
        client.request {
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
    }
}