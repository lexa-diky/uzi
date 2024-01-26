package io.github.lexadiky.uzi.engine.execution.http

import io.github.lexadiky.uzi.engine.execution.ExecutionPlan
import io.github.lexadiky.uzi.engine.task.HttpCallTask
import io.ktor.client.HttpClient
import io.ktor.client.request.headers
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.http.HttpMethod

internal class HttpExecutionPlan(
    private val task: HttpCallTask,
    private val client: HttpClient
) : ExecutionPlan {

    override suspend fun execute() {
        client.request {
            method = HttpMethod(task.method)
            url(task.uri.toURL())
            headers {
                task.headers.forEach { (key, values) ->
                    values.forEach { value ->
                        append(key, value)
                    }
                }
            }
        }
    }
}