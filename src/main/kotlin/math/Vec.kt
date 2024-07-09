package ge.nika.math

import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

data class Vec(
    val x: Double,
    val y: Double,
) {
    operator fun times(number: Double): Vec {
        return Vec(x * number, y * number)
    }

    operator fun plus(other: Vec): Vec {
        return Vec(
            x = x + other.x,
            y = y + other.y
        )
    }

    fun distanceTo(other: Vec): Double {
        val cat1 = abs(this.x - other.x)
        val cat2 = abs(this.y - other.y)

        return sqrt(
            cat1.pow(2) + cat2.pow(2)
        )
    }
}