package io.github.lexadiky.uzi.engine.execution.impl

import io.github.lexadiky.uzi.engine.execution.ExecutionPlan
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