package io.github.lexadiky.uzi.agent

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
    val planner = ExecutionPlanner.default()
    val task = UziTask {
        repeat(10) {
            http {
                frequency { normal(mean = 1.seconds, stdDeviation = 1.seconds) }
                repeats(1u)
                uri("https://agoda.com")
            }
        }
    }

    val plan = planner.plan(task)
    runBlocking {
        println("started")
        val executionContext = ExecutionContext.root(UUID.randomUUID())
        plan.execute(executionContext)
        println("finished ${executionContext.measurements}")

        executionContext.measurements.forEach {
            printMeasurements(0, it)
        }
    }
}

private fun printMeasurements(level: Int, measurement: Measurement) {
    print(" ".repeat(level))
    print("${measurement.typeId}/${measurement.tag}")
    when (val value = measurement.value) {
        is Measurement.Value.Duration -> println(value.value)
        is Measurement.Value.Scalar -> println(value.value)
        is Measurement.Value.Group -> {
            value.nodes.forEach { printMeasurements(level + 1, it) }
        }
    }
    println()
}
