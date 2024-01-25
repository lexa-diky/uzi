package io.github.lexadiky.uzi.engine.task

data class ParallelTask(
    val children: List<UziTask>
) : UziTask