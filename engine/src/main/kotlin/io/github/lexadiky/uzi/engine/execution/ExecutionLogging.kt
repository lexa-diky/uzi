package io.github.lexadiky.uzi.engine.execution

import org.slf4j.LoggerFactory
import kotlin.time.measureTime

suspend fun ExecutionContext.trace(caller: Any, runnable: suspend () -> Unit) {
    val logger = LoggerFactory.getLogger(caller::class.java.simpleName)
    logger.trace("running ${this.serialId}")
    val time = measureTime { runnable() }
    logger.trace("finished ${this.serialId} in $time")
}