package io.github.lexadiky.uzi.engine.task.builder

import io.github.lexadiky.uzi.engine.util.DurationFrequency
import io.github.lexadiky.uzi.engine.util.MonotoneFrequency
import io.github.lexadiky.uzi.engine.util.NormalFrequency
import kotlin.time.Duration

class FrequencyBuilder {
    private var selected: DurationFrequency? = null

    fun monotone(every: Duration) {
        selected = MonotoneFrequency(every)
    }

    fun normal(mean: Duration, stdDeviation: Duration) {
        selected = NormalFrequency(mean, stdDeviation)
    }

    fun build(): DurationFrequency = requireNotNull(selected)
}