package io.github.lexadiky.uzi.engine.task.builder

import io.github.lexadiky.uzi.engine.task.HttpCallTask
import io.github.lexadiky.uzi.engine.task.ParallelTask
import io.github.lexadiky.uzi.engine.task.RepeatedTimerTask
import io.github.lexadiky.uzi.engine.task.UziTask
import io.github.lexadiky.uzi.engine.util.Monotone
import java.net.URI
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.seconds

class HttpRequestTaskBuilderTest {

    @Test
    fun `GIVEN basic http build THEN return data`() {
        val task = UziTask {
            http {
                frequency { monotone(every = 1.seconds) }
                repeats(1_000_000u)
                uri("https://google.com")
                method("POST")
                header("AGENT", "uzi")
            }
        }

        assertEquals(
            expected = ParallelTask(
                children = listOf(
                    RepeatedTimerTask(
                        frequency = Monotone(every = 1.seconds),
                        repeats = 1_000_000u,
                        child = HttpCallTask(
                            uri = URI.create("https://google.com"),
                            method = "POST",
                            headers = mapOf(
                                "AGENT" to listOf("uzi")
                            )
                        )
                    )
                )
            ),
            actual = task
        )
    }
}