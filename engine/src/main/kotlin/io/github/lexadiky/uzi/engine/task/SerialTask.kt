package io.github.lexadiky.uzi.engine.task

data class SerialTask(
    private val children: List<Task>
) : Task