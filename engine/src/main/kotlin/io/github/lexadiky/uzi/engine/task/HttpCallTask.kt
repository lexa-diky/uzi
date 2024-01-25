package io.github.lexadiky.uzi.engine.task

import io.github.lexadiky.uzi.engine.task.serialization.URISerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.net.URI

@[Serializable SerialName("httpCall")]
data class HttpCallTask(
    @[Serializable(URISerializer::class) SerialName("uri")]
    val uri: URI,
    @SerialName("method")
    val method: String,
    @SerialName("headers")
    val headers: Map<String, List<String>>
) : UziTask
