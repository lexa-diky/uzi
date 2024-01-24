package io.github.lexadiky.uzi.engine.task.balancer

import io.github.lexadiky.uzi.engine.task.UziTask
import io.github.lexadiky.uzi.engine.task.builder.http
import io.github.lexadiky.uzi.engine.task.builder.invoke
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.expect
import kotlin.time.Duration.Companion.seconds

class GrugTaskBalancerTest {

    @Test
    fun `GIVEN parallel task THEN balance into subtasks`() {
        val grug = GrugTaskBalancer()
        val balanced = grug.balance(partitions = 10, task = UziTask {
            repeat(3) {
                http {
                    frequency { monotone(every = 10.seconds) }
                    repeats(100_000u)
                    uri("http://google.com")
                }
            }
        })

        assertEquals(
            expected = 30,
            actual = balanced.size
        )
    }
}