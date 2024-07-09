package ge.nika.math

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
}