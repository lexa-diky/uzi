package io.github.lexadiky.uzi.engine.util

import kotlin.time.Duration

sealed interface DurationFrequency {

    fun split(parts: Int): DurationFrequency
}

data class MonotoneFrequency(val every: Duration): DurationFrequency {

    override fun split(parts: Int): DurationFrequency {
        return MonotoneFrequency(every * parts)
    }
}

data class NormalFrequency(val mean: Duration, val stdDeviation: Duration): DurationFrequency {

    override fun split(parts: Int): DurationFrequency {
        // TODO check algorithm
        return NormalFrequency(mean = mean * parts, stdDeviation = stdDeviation * parts)
    }
}