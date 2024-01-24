package io.github.lexadiky.uzi.engine.task.builder

import io.github.lexadiky.uzi.engine.task.NoopTask
import io.github.lexadiky.uzi.engine.task.ParallelTask
import io.github.lexadiky.uzi.engine.task.UziTask
import kotlin.test.Test
import kotlin.test.assertEquals

class UziTaskBuilderTest {

    @Test
    fun `GIVEN no subtasks THEN returns empty parallel task`() {
        val task = UziTask { /* nothing here */ }
        assertEquals(
            expected = ParallelTask(emptyList()),
            actual = task
        )
    }

    @Test
    fun `GIVEN some amount of children THEN returns parallel task with this children`() {
        val task = UziTask {
            child(NoopTask)
            child(NoopTask)
        }

        assertEquals(
            expected = ParallelTask(listOf(NoopTask, NoopTask)),
            actual = task
        )
    }
}