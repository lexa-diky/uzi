package io.github.lexadiky.uzi.engine.task.balancer

import io.github.lexadiky.uzi.engine.task.UziTask

/**
 * Splits [UziTask] into subtasks that are equally (defined by implementation) split
 * by some criteria (defined by implementation).
 *
 * All implementations guarantee that split result summary (when parallely executed) will be
 * as close as possible (defined by implementation) to input.
 *
 * Number of subtasks is not defined, and depends only on input.
 * It is up to a higher layer of abstraction to group returned subtasks to be sent into agents.
 */
interface TaskBalancer {

    /**
     * @param task [UziTask] to be split into subtasks
     * @return [List] of split [UziTask]. If no split possible will return list with single input [task]
     */
    fun balance(task: UziTask): List<UziTask>

    companion object {

        /**
         * Returns default implementation of [TaskBalancer].
         *
         * If you are unsure about the specific one that you need, use this one.
         */
        fun default(): TaskBalancer = GrugTaskBalancer()
    }
}