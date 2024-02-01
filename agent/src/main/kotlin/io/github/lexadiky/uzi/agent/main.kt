package io.github.lexadiky.uzi.agent

import io.github.lexadiky.uzi.engine.execution.ExecutionContext
import io.github.lexadiky.uzi.engine.execution.ExecutionPlanner
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
                repeats(10u)
                uri("https://agoda.com")
            }
        }
    }

    val plan = planner.plan(task)
    runBlocking {
        println("started")
        plan.execute(ExecutionContext.root(UUID.randomUUID()))
        println("finished")
    }
}