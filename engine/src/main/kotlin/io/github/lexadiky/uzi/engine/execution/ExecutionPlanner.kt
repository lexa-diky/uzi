package io.github.lexadiky.uzi.engine.execution

import io.github.lexadiky.uzi.engine.task.UziTask

/**
 * Converter from static [UziTask] to dynamic [ExecutionPlan]
 */
interface ExecutionPlanner<Task: UziTask> {

    fun plan(task: Task): ExecutionPlan
}