package io.github.lexadiky.uzi.engine.task

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@[Serializable SerialName("noop")]
data object NoopTask : UziTask