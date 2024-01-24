package io.github.lexadiky.uzi.engine.task.builder

import io.github.lexadiky.uzi.engine.task.UziTask

sealed interface UziTaskBuilder {

    fun build(): UziTask
}

operator fun UziTask.Companion.invoke(builder: ParallelUziTaskBuilder.() -> Unit): UziTask {
    return ParallelUziTaskBuilder().apply(builder).build()
}