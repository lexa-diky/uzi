package io.github.lexadiky.uzi.agent

import io.github.lexadiky.uzi.agent.server.UziAgent
import io.github.lexadiky.uzi.agent.util.MeasurementsUtil
import io.github.lexadiky.uzi.engine.execution.ExecutionContext
import io.github.lexadiky.uzi.engine.execution.ExecutionPlanner
import io.github.lexadiky.uzi.engine.measurement.Measurement
import io.github.lexadiky.uzi.engine.task.UziTask
import io.github.lexadiky.uzi.engine.task.builder.http
import io.github.lexadiky.uzi.engine.task.builder.invoke
import kotlinx.coroutines.runBlocking
import java.util.UUID
import kotlin.time.Duration.Companion.seconds

fun main() {
    UziAgent(port = 8082)
        .start()
}
