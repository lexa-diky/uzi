package io.github.lexadiky.uzi.engine.execution

/**
 * Component responsible for executing test.
 */
interface ExecutionPlan {

    suspend fun execute(context: ExecutionContext)
}