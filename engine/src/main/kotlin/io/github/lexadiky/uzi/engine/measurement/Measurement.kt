package io.github.lexadiky.uzi.engine.measurement

data class Measurement(
    val typeId: String,
    val tag: String,
    val value: Value,
) {

    sealed interface Value {

        data class Duration(val value: kotlin.time.Duration): Value

        data class Scalar(val value: Long) : Value

        data class Fact(val value: String): Value

        data class Group(val nodes: List<Measurement>): Value
    }
}