package io.github.lexadiky.uzi.engine.execution

import io.github.lexadiky.uzi.engine.task.UziTask

/**
 * Converter from static [UziTask] to dynamic [ExecutionPlan]
 */
interface ExecutionPlanner {

    fun plan(task: UziTask): ExecutionPlan
}