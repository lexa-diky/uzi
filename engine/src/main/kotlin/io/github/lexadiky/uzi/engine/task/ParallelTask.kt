package io.github.lexadiky.uzi.engine.task

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@[Serializable SerialName("parallel")]
data class ParallelTask(
    @SerialName("children")
    val children: List<UziTask>
) : UziTask
