package io.github.lexadiky.uzi.engine.task.builder

import io.github.lexadiky.uzi.engine.util.DurationFrequency

abstract class RepeatedTimerTaskBuilder : UziTaskBuilder {
    protected var frequency: DurationFrequency? = null
    protected var repeats: UInt = 0u

    fun frequency(build: FrequencyBuilder.() -> Unit) {
        frequency = FrequencyBuilder().apply(build).build()
    }

    fun repeats(value: UInt) {
        repeats = value
    }
}