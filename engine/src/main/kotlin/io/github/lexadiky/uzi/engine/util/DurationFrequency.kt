package io.github.lexadiky.uzi.engine.util

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Duration

@Serializable
sealed interface DurationFrequency {

    fun split(parts: Int): DurationFrequency
}

@[Serializable SerialName("monotone")]
data class MonotoneFrequency(
    @SerialName("every") val every: Duration
) : DurationFrequency {

    override fun split(parts: Int): DurationFrequency {
        return MonotoneFrequency(every * parts)
    }
}

@[Serializable SerialName("normal")]
data class NormalFrequency(
    @SerialName("mean") val mean: Duration,
    @SerialName("stdDeviation") val stdDeviation: Duration
) : DurationFrequency {

    override fun split(parts: Int): DurationFrequency {
        // TODO check algorithm
        return NormalFrequency(mean = mean * parts, stdDeviation = stdDeviation * parts)
    }
}