package io.github.lexadiky.uzi.engine.task

import io.github.lexadiky.uzi.engine.util.Frequency

data class RepeatedTimerTask(
    val child: Task,
    val frequency: Frequency,
    val repeats: UInt
): Task