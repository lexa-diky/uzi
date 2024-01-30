package io.github.lexadiky.uzi.engine.execution

import org.slf4j.LoggerFactory
import kotlin.time.measureTime

suspend fun ExecutionContext.logged(caller: Any, runnable: suspend () -> Unit) {
    val logger = LoggerFactory.getLogger(caller::class.java.simpleName)
    logger.debug("running ${this.serialId}")
    val time = measureTime { runnable() }
    logger.debug("finished ${this.serialId} in $time")
}