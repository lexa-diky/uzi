package io.github.lexadiky.uzi.engine.execution

import io.github.lexadiky.uzi.engine.measurement.Measurement
import java.util.UUID
import java.util.concurrent.atomic.AtomicLong

interface ExecutionContext {
    val serialId: Long
    val sessionId: UUID
    val uuid: UUID

    fun branch(): ExecutionContext

    fun measure(measurement: Measurement)

    companion object {

        fun root(sessionId: UUID): ExecutionContext {
            return ExecutionContextImpl(sessionId = sessionId)
        }
    }
}

private data class ExecutionContextImpl(
    override val serialId: Long = atomicCounter.getAndIncrement(),
    override val uuid: UUID = UUID.randomUUID(),
    override val sessionId: UUID,
    private var measurements: MutableList<Measurement> = ArrayList(),
) : ExecutionContext {

    override fun branch(): ExecutionContext {
        return copy(
            serialId = atomicCounter.getAndIncrement(),
            uuid = UUID.randomUUID()
        )
    }

    override fun measure(measurement: Measurement) {
        measurements += measurement
    }

    companion object {

        internal val atomicCounter: AtomicLong = AtomicLong(0)
    }
}
