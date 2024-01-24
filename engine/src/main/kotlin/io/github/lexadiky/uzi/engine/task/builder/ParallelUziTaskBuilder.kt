package io.github.lexadiky.uzi.engine.task.builder

import io.github.lexadiky.uzi.engine.task.ParallelTask
import io.github.lexadiky.uzi.engine.task.UziTask

class ParallelUziTaskBuilder : UziTaskBuilder {
    private val children: ArrayList<UziTask> = ArrayList()

    fun child(task: UziTask) {
        children += task
    }

    override fun build(): UziTask {
        return ParallelTask(children)
    }
}