package io.github.lexadiky.uzi.engine.task.builder

import io.github.lexadiky.uzi.engine.task.HttpCallTask
import io.github.lexadiky.uzi.engine.task.ParallelTask
import io.github.lexadiky.uzi.engine.task.RepeatedTimerTask
import io.github.lexadiky.uzi.engine.task.UziTask
import io.github.lexadiky.uzi.engine.util.MonotoneFrequency
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
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
                        frequency = MonotoneFrequency(every = 1.seconds),
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

    @Test
    fun `GIVEN basic http build THEN it is serializable`() {
        val json = Json { prettyPrint = true }
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
            expected = """
                {
                    "type": "parallel",
                    "children": [
                        {
                            "type": "repeated-timer",
                            "child": {
                                "type": "httpCall",
                                "uri": "https://google.com",
                                "method": "POST",
                                "headers": {
                                    "AGENT": [
                                        "uzi"
                                    ]
                                }
                            },
                            "frequency": {
                                "type": "monotone",
                                "every": "PT1S"
                            },
                            "repeats": 1000000
                        }
                    ]
                }
            """.trimIndent(),
            actual = json.encodeToString(task)
        )
    }
}