package io.github.lexadiky.uzi.engine.task

import java.net.URI

data class HttpCallTask(
    val uri: URI,
    val method: String,
    val headers: Map<String, String>
): UziTask
