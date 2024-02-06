package io.github.lexadiky.uzi.agent.processor

import io.github.lexadiky.uzi.agent.util.MeasurementsUtil
import io.github.lexadiky.uzi.engine.execution.ExecutionContext
import io.github.lexadiky.uzi.engine.execution.ExecutionPlanner
import io.github.lexadiky.uzi.engine.task.UziTask
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.UUID

class SessionProcessor {
    private val sessionMutex = Mutex()
    private val planner = ExecutionPlanner.default()

    suspend fun enqueue(sessionUuid: UUID, task: UziTask): Job {
        return sessionMutex.withLock {
            coroutineScope {
                launch(SupervisorJob()) {
                    val context: ExecutionContext = ExecutionContext.root(sessionUuid)
                    val plan = planner.plan(task)
                    plan.execute(context)
                    context.measurements.forEach { measurement ->
                        MeasurementsUtil.printMeasurements(0, measurement)
                    }
                }
            }
        }
    }
}