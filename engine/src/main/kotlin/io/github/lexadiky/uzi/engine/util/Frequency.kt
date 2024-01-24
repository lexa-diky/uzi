package io.github.lexadiky.uzi.engine.util

import kotlin.time.Duration

sealed interface Frequency

data class Monotone(val every: Duration): Frequency

data class Normal(val mean: Duration, val stdDeviation: Duration): Frequency