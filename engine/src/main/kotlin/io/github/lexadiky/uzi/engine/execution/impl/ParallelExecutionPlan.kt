package io.github.lexadiky.uzi.engine.execution.impl

import io.github.lexadiky.uzi.engine.execution.ExecutionContext
import io.github.lexadiky.uzi.engine.execution.ExecutionPlan
import io.github.lexadiky.uzi.engine.execution.trace
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class ParallelExecutionPlan(
    private val subPlans: List<ExecutionPlan>
) : ExecutionPlan {

    override suspend fun execute(context: ExecutionContext) = context.trace(this) {
        context.branch("parallel") { context ->
            coroutineScope {
                subPlans.map { plan ->
                    async {
                        context.branch("parallel-node", plan::execute)
                    }
                }.awaitAll()
            }
        }
    }
}