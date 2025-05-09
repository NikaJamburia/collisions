package ge.nika.frame

import ge.nika.math.Vec

class Particle (
    val radius: Double,
    val color: String = "White",
    val mass: Double = 1.0,
    val acceleration: Vec = gravity,
    startingPosition: Vec,
    startingVelocity: Vec,
    val printOnUpdate: Boolean = false,
    ) {
    var position: Vec = startingPosition
        private set
    var velocity: Vec = startingVelocity
        private set

    fun updateDynamics(dt: Double, newVelocity: Vec? = null) {
        velocity = newVelocity ?: velocity
        position += velocity * dt
        velocity += acceleration * dt
        if (printOnUpdate) {
            println(velocity)
        }
    }

    fun flipXVelocity(damping: Double = 1.0) {
        velocity = Vec(-velocity.x * damping, velocity.y)
    }

    fun flipYVelocity(
        dampingX: Double = 1.0,
        dampingY: Double = 1.0,
    ) {
        velocity = Vec(velocity.x * dampingX, -velocity.y * dampingY)
    }

    fun collidesWith(otherParticle: Particle): Boolean {
        return this.position.distanceTo(otherParticle.position) <= radius + otherParticle.radius
    }

    val x: Double
        get() = position.x

    val y: Double
        get() = position.y
}

val gravity: Vec = Vec(x = 0.0, y = 500.0)