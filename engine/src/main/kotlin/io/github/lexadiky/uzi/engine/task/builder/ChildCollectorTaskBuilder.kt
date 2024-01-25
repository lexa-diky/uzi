package io.github.lexadiky.uzi.engine.task.builder

import io.github.lexadiky.uzi.engine.task.UziTask

interface ChildCollectorTaskBuilder {

    fun child(task: UziTask)
}