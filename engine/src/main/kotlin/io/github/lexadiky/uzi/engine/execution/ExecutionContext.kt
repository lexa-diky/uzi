package io.github.lexadiky.uzi.engine.execution

import io.github.lexadiky.uzi.engine.measurement.Measurement
import java.util.UUID
import java.util.concurrent.atomic.AtomicLong

interface ExecutionContext {
    val serialId: Long
    val sessionId: UUID
    val uuid: UUID
    val measurements: List<Measurement>

    suspend fun branch(typeId: String, scope: suspend (ExecutionContext) -> Unit): ExecutionContext

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
) : ExecutionContext {
    override var measurements: MutableList<Measurement> = ArrayList()
        private set

    override suspend fun branch(typeId: String, scope: suspend (ExecutionContext) -> Unit): ExecutionContext {
        val childContext = copy(
            serialId = atomicCounter.getAndIncrement(),
            uuid = UUID.randomUUID()
        )

        scope(childContext)

        measure(
            Measurement(
                typeId = typeId,
                tag = "${typeId}_$serialId",
                value = Measurement.Value.Group(childContext.measurements)
            )
        )

        return this
    }

    override fun measure(measurement: Measurement) {
        measurements += measurement
    }

    companion object {

        internal val atomicCounter: AtomicLong = AtomicLong(0)
    }
}
