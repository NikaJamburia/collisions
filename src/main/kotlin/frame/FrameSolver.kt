package ge.nika.frame

import ge.nika.math.Vec
import kotlin.math.pow

class FrameSolver(
    private val frameXBounds: ClosedRange<Double>,
    private val frameYBounds: ClosedRange<Double>,
    private val particles: List<Particle>,
) {
    fun updateParticleDynamics(fps: Double) {
        val dt = 1 / fps

        particles.forEach { particle ->
            particle.updateDynamics(dt)

            if (particle.isOutOfX()) {
                particle.flipXVelocity()
            }
            if (particle.isOutOfY()) {
                particle.flipYVelocity()
            }


            particles.forEach { otherParticle ->
                if (otherParticle != particle) {
                    if (particle.collidesWith(otherParticle)) {
                        val newVelocity1 = particle.calculateVelocityAfterCollisionWith(otherParticle)
                        val newVelocity2 = otherParticle.calculateVelocityAfterCollisionWith(particle)

                        particle.updateDynamics(dt = dt, newVelocity = newVelocity1)
                        otherParticle.updateDynamics(dt = dt, newVelocity = newVelocity2)
                    }
                }
            }
        }

    }

    private fun Particle.calculateVelocityAfterCollisionWith(otherParticle: Particle): Vec {
        val positionDiff = position - otherParticle.position
        val massDiff = 2 * otherParticle.mass / (mass + otherParticle.mass)
        val velocityDiff = velocity - otherParticle.velocity
        val idk = velocityDiff.dot(positionDiff) / positionDiff.magnitude().pow(2)
        val result = velocity - (positionDiff * massDiff * idk)
        println(result)
        return result
    }

    private fun Particle.isOutOfX(): Boolean = x !in xBoundsFor(this)
    private fun Particle.isOutOfY(): Boolean = y !in yBoundsFor(this)

    private fun xBoundsFor(particle: Particle): ClosedRange<Double> =
        (frameXBounds.start + particle.radius) .. (frameXBounds.endInclusive - particle.radius)

    private fun yBoundsFor(particle: Particle): ClosedRange<Double> =
        (frameYBounds.start + particle.radius) .. (frameYBounds.endInclusive - particle.radius)
}