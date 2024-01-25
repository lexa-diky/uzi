package io.github.lexadiky.uzi.engine.task.balancer

import io.github.lexadiky.uzi.engine.task.ParallelTask
import io.github.lexadiky.uzi.engine.task.UziTask

/**
 * Basic implementation that just splits tasks by explicitly declared parallelism.
 */
internal class GrugTaskBalancer : TaskBalancer {

    override fun balance(task: UziTask, partitions: Int): List<UziTask> {
        if (task is ParallelTask) {
            return task.children
        }
        return listOf(task)
    }
}