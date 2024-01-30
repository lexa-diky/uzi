package io.github.lexadiky.uzi.engine.execution.impl

import io.github.lexadiky.uzi.engine.execution.ExecutionContext
import io.github.lexadiky.uzi.engine.execution.ExecutionPlan

class NoopExecutionPlan : ExecutionPlan {

    override suspend fun execute(context: ExecutionContext) = Unit
}