package io.github.lexadiky.uzi.engine.execution.impl

import io.github.lexadiky.uzi.engine.execution.ExecutionPlan
import io.github.lexadiky.uzi.engine.util.DurationFrequency
import io.github.lexadiky.uzi.engine.util.MonotoneFrequency
import io.github.lexadiky.uzi.engine.util.NormalFrequency
import kotlinx.coroutines.delay
import java.util.Random
import kotlin.time.DurationUnit

class RepeatedTimerExecutionPlan(
    private val frequency: DurationFrequency,
    private val repeats: UInt,
    private val subPlan: ExecutionPlan,
    private val random: Random
) : ExecutionPlan {

    override suspend fun execute() {
        repeat(repeats.toInt()) {
            delayFrequency()
            subPlan.execute()
        }
    }

    private suspend fun delayFrequency() {
        when (frequency) {
            is MonotoneFrequency -> delayMonotone(frequency)
            is NormalFrequency -> delayNormal(frequency)
        }
    }

    private suspend fun delayNormal(frequency: NormalFrequency) {
        delay(
            random.nextGaussian(
                frequency.mean.toDouble(DurationUnit.MILLISECONDS),
                frequency.stdDeviation.toDouble(DurationUnit.MILLISECONDS)
            ).toLong()
        )
    }

    private suspend fun delayMonotone(frequency: MonotoneFrequency) {
        delay(frequency.every)
    }
}