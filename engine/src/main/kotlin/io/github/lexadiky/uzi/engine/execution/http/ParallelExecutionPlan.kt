package io.github.lexadiky.uzi.engine.execution.http

import io.github.lexadiky.uzi.engine.execution.ExecutionPlan
import io.github.lexadiky.uzi.engine.execution.ExecutionPlanner
import io.github.lexadiky.uzi.engine.task.ParallelTask
import io.github.lexadiky.uzi.engine.task.UziTask
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class ParallelExecutionPlan(
    private val subPlans: List<ExecutionPlan>
) : ExecutionPlan {

    override suspend fun execute() {
        coroutineScope {
            subPlans.map { plan -> async { plan.execute() } }
                .awaitAll()
        }
    }
}