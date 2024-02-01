package io.github.lexadiky.uzi.engine.execution

interface PlanningContext {
    val seed: Int

    companion object {

        fun deterministic(seed: Int = 0): PlanningContext {
            return PlanningContextImpl(seed)
        }
    }
}

private class PlanningContextImpl(
    override val seed: Int
) : PlanningContext