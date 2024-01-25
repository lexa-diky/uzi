package io.github.lexadiky.uzi.engine.task

data class ParallelTask(
    private val children: List<Task>
) : Task