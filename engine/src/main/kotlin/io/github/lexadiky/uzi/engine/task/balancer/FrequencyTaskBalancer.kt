package io.github.lexadiky.uzi.engine.task.balancer

import io.github.lexadiky.uzi.engine.task.RepeatedTimerTask
import io.github.lexadiky.uzi.engine.task.UziTask

internal class FrequencyTaskBalancer : TaskBalancer {

    override fun balance(task: UziTask, partitions: Int): List<UziTask> {
        if (task is RepeatedTimerTask) {
            val partitioned = task.frequency.split(partitions)
            return List(partitions) {
                task.copy(frequency = partitioned)
            }
        } else {
            return listOf(task)
        }
    }
}