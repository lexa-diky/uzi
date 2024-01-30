package io.github.lexadiky.uzi.engine.execution.impl

import io.github.lexadiky.uzi.engine.execution.ExecutionPlan
import io.github.lexadiky.uzi.engine.execution.ExecutionPlanner
import io.github.lexadiky.uzi.engine.task.HttpCallTask
import io.github.lexadiky.uzi.engine.task.NoopTask
import io.github.lexadiky.uzi.engine.task.ParallelTask
import io.github.lexadiky.uzi.engine.task.RepeatedTimerTask
import io.github.lexadiky.uzi.engine.task.UziTask
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import java.util.Random

class GrugExecutionPlanner : ExecutionPlanner<UziTask> {

    private val httpClient: HttpClient = HttpClient(OkHttp)
    private val random: Random = Random(System.currentTimeMillis())

    override fun plan(task: UziTask): ExecutionPlan {
        return when (task) {
            is HttpCallTask -> planHttpCall(task)
            is NoopTask -> planNoop(task)
            is ParallelTask -> planParallel(task)
            is RepeatedTimerTask -> planRepeatedTimer(task)
        }
    }

    private fun planRepeatedTimer(task: RepeatedTimerTask): ExecutionPlan {
        return RepeatedTimerExecutionPlan(
            frequency = task.frequency,
            repeats = task.repeats,
            subPlan = plan(task.child),
            random = random
        )
    }

    private fun planParallel(task: ParallelTask): ExecutionPlan {
        return ParallelExecutionPlan(task.children.map(::plan))
    }

    private fun planNoop(task: NoopTask): ExecutionPlan {
        return NoopExecutionPlan()
    }

    private fun planHttpCall(task: HttpCallTask): ExecutionPlan {
        return HttpExecutionPlan(
            uri = task.uri,
            method = task.method,
            headers = task.headers,
            client = httpClient
        )
    }
}