package io.github.lexadiky.uzi.engine.task.balancer

import io.github.lexadiky.uzi.engine.task.HttpCallTask
import io.github.lexadiky.uzi.engine.task.NoopTask
import io.github.lexadiky.uzi.engine.task.ParallelTask
import io.github.lexadiky.uzi.engine.task.RepeatedTimerTask
import io.github.lexadiky.uzi.engine.task.UziTask

/**
 * Default [TaskBalancer]
 */
internal class GrugTaskBalancer : TaskBalancer {
    private val frequencyTaskBalancer = FrequencyTaskBalancer()

    override fun balance(task: UziTask, partitions: Int): List<UziTask> {
        var previousStep: List<UziTask> = listOf(task)
        var balancingTokens = partitions.coerceAtLeast(1) // to prevent too many looping

        while (balancingTokens > 0) {
            val balanced = previousStep.flatMap { balanceImpl(task, partitions) }
            if (balanced == previousStep || balanced.size >= partitions) {
                return balanced
            }
            previousStep = balanced
            balancingTokens -= 1
        }

        return previousStep
    }


    private fun balanceImpl(task: UziTask, partitions: Int): List<UziTask> = when (task) {
        is HttpCallTask -> noopBalance(task)
        NoopTask -> noopBalance(task)
        is ParallelTask -> parallelTaskBalance(task)
            .flatMap { balanceImpl(it, partitions) }
        is RepeatedTimerTask -> frequencyTaskBalancer.balance(task, partitions)
    }

    private fun noopBalance(task: UziTask): List<UziTask> {
        return listOf(task)
    }

    private fun parallelTaskBalance(task: ParallelTask): List<UziTask> {
        return task.children
    }
}