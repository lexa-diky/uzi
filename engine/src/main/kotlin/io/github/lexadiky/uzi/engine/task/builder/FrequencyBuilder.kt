package io.github.lexadiky.uzi.engine.task.builder

import io.github.lexadiky.uzi.engine.util.Frequency
import io.github.lexadiky.uzi.engine.util.Monotone
import io.github.lexadiky.uzi.engine.util.Normal
import kotlin.time.Duration

class FrequencyBuilder {
    private var selected: Frequency? = null

    fun monotone(every: Duration) {
        selected = Monotone(every)
    }

    fun normal(mean: Duration, stdDeviation: Duration) {
        selected = Normal(mean, stdDeviation)
    }

    fun build(): Frequency = requireNotNull(selected)
}