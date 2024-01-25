package io.github.lexadiky.uzi.engine.task.builder

import io.github.lexadiky.uzi.engine.task.HttpCallTask
import io.github.lexadiky.uzi.engine.task.RepeatedTimerTask
import io.github.lexadiky.uzi.engine.task.UziTask
import io.ktor.http.HttpMethod
import java.net.URI

class HttpRequestTaskBuilder : RepeatedTimerTaskBuilder() {
    private var uri: URI? = null
    private var method: String = HttpMethod.Get.value
    private val headers: MutableMap<String, MutableList<String>> = HashMap()

    fun uri(string: String) {
        uri = URI.create(string)
    }

    fun method(value: String) {
        method = value
    }

    fun header(key: String, value: String) {
        headers.computeIfAbsent(key) { ArrayList() }
            .add(value)
    }

    override fun build(): UziTask {
        return RepeatedTimerTask(
            frequency = requireNotNull(frequency),
            repeats = requireNotNull(repeats),
            child = HttpCallTask(
                uri = requireNotNull(uri),
                method = method,
                headers = headers
            )
        )
    }
}

fun ChildCollectorTaskBuilder.http(build: HttpRequestTaskBuilder.() -> Unit) {
    child(HttpRequestTaskBuilder().apply(build).build())
}
