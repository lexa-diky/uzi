package io.github.lexadiky.uzi.engine.task

import io.github.lexadiky.uzi.engine.util.DurationFrequency
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@[Serializable SerialName("repeated-timer")]
data class RepeatedTimerTask(
    @SerialName("child")
    val child: UziTask,
    @SerialName("frequency")
    val frequency: DurationFrequency,
    @SerialName("repeats")
    val repeats: UInt
): UziTask
