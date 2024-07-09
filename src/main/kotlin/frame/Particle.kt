package ge.nika.frame

import ge.nika.math.Vec

class Particle (
    val radius: Double,
    val color: String = "White",
    startingPosition: Vec,
    startingVelocity: Vec,
) {
    var position: Vec = startingPosition
        private set
    var velocity: Vec = startingVelocity
        private set

    val acceleration: Vec = gravity

    fun updateDynamics(time: Double) {
        position += velocity * time
        velocity += acceleration * time
    }

    fun flipXVelocity(damping: Double = 1.0) {
        velocity = Vec(-velocity.x * damping, velocity.y)
        println(position)

    }

    fun flipYVelocity(
        dampingX: Double = 1.0,
        dampingY: Double = 1.0,
    ) {
        velocity = Vec(velocity.x * dampingX, -velocity.y * dampingY)
    }

    val x: Double
        get() = position.x

    val y: Double
        get() = position.y
}

val gravity: Vec = Vec(x = 0.0, y = 500.0)