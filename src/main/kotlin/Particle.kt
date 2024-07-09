package ge.nika

import ge.nika.math.Vec

class Particle (
    val radius: Double,
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
    }

    fun flipYVelocity(damping: Double = 1.0) {
        velocity = Vec(velocity.x * damping, -velocity.y * damping)
        println(velocity)
    }

    val x: Double
        get() = position.x

    val y: Double
        get() = position.y
}

val gravity: Vec = Vec(x = 0.0, y = 500.0)