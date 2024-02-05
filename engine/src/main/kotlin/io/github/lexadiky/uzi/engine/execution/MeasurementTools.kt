package io.github.lexadiky.uzi.engine.execution

import io.github.lexadiky.uzi.engine.measurement.Measurement
import kotlin.time.measureTimedValue

suspend fun <T> ExecutionContext.measureTime(typeId: String, action: suspend () -> T): T {
    val (res, time) = measureTimedValue {
        action()
    }

    measure(Measurement(typeId, Measurement.Value.Duration(time)))

    return res
}