package io.github.lexadiky.uzi.engine.task

import io.github.lexadiky.uzi.engine.util.Frequency

data class RepeatedTimerTask(
    val child: UziTask,
    val frequency: Frequency,
    val repeats: UInt
): UziTask