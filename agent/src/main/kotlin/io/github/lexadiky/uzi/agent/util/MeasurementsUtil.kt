package io.github.lexadiky.uzi.agent.util

import io.github.lexadiky.uzi.engine.measurement.Measurement

object MeasurementsUtil {

    fun printMeasurements(level: Int, measurement: Measurement) {
        print(" ".repeat(level))

        if (measurement.typeId != measurement.tag) {
            print("${measurement.typeId}/${measurement.tag}")
        } else {
            print(measurement.typeId)
        }

        when (val value = measurement.value) {
            is Measurement.Value.Duration -> println(" : ${value.value}")
            is Measurement.Value.Scalar -> println(" : ${value.value}")
            is Measurement.Value.Fact -> println(" : ${value.value}")
            is Measurement.Value.Group -> printGroupValue(value, level)
        }
    }

    private const val COMPRESSION_FLAG = true
    private fun printGroupValue(value: Measurement.Value.Group, level: Int) {
        if (value.nodes.size == 1 && COMPRESSION_FLAG) {
            printCompressedGroupValue(value, level)
        } else {
            printUncompressedGroupValue(value, level)
        }
    }

    private fun printCompressedGroupValue(value: Measurement.Value.Group, level: Int) {
        print(" :>")
        printMeasurements(level, value.nodes.first())
    }

    private fun printUncompressedGroupValue(value: Measurement.Value.Group, level: Int) {
        println(" : { ")
        value.nodes.forEach { printMeasurements(level + 1, it) }
        print(" ".repeat(level))
        println("}")
    }
}