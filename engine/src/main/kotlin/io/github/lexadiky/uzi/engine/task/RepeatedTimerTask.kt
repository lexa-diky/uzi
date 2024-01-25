package io.github.lexadiky.uzi.engine.task

import io.github.lexadiky.uzi.engine.util.DurationFrequency

data class RepeatedTimerTask(
    val child: UziTask,
    val frequency: DurationFrequency,
    val repeats: UInt
): UziTask