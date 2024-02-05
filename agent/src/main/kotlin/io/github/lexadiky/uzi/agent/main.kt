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
        repeat(3) {
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

    if (measurement.typeId != measurement.tag) {
        print("${measurement.typeId}/${measurement.tag}")
    } else {
        print(measurement.typeId)
    }

    when (val value = measurement.value) {
        is Measurement.Value.Duration -> println(" : ${value.value}")
        is Measurement.Value.Scalar -> println(" : ${value.value}")
        is Measurement.Value.Fact -> println(" : ${value.value}")
        is Measurement.Value.Group -> printGroupValue(value, level)
    }
}

private const val COMPRESSION_FLAG = true
private fun printGroupValue(value: Measurement.Value.Group, level: Int) {
    if (value.nodes.size == 1 && COMPRESSION_FLAG) {
        printCompressedGroupValue(value, level)
    } else {
        printUncompressedGroupValue(value, level)
    }
}

private fun printCompressedGroupValue(value: Measurement.Value.Group, level: Int) {
    print(" :>")
    printMeasurements(level, value.nodes.first())
}

private fun printUncompressedGroupValue(value: Measurement.Value.Group, level: Int) {
    println(" : { ")
    value.nodes.forEach { printMeasurements(level + 1, it) }
    print(" ".repeat(level))
    println("}")
}
