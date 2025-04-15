package ge.nika.frame

import ge.nika.collision.CollisionResolver

class FrameSolver(
    private val frameXBounds: ClosedRange<Double>,
    private val frameYBounds: ClosedRange<Double>,
    private val particles: List<Particle>,
    private val collisionResolver: CollisionResolver,
) {

    private val particleXBounds: Map<Particle, ClosedRange<Double>> = particles.associateWith {
        (frameXBounds.start + it.radius) .. (frameXBounds.endInclusive - it.radius)
    }
    
    private val particleYBounds: Map<Particle, ClosedRange<Double>> = particles.associateWith {
        (frameYBounds.start + it.radius)..(frameYBounds.endInclusive - it.radius)
    }

    fun updateParticleDynamics(fps: Double) {
        val dt = 1 / fps

        particles.forEach { particle ->
            particle.updateDynamics(dt)

            particle.bounceOfXWalls()
            particle.bounceOfYWalls()

            particles.forEach { otherParticle ->
                if (otherParticle != particle) {
                    if (particle.collidesWith(otherParticle)) {
                        val resolution = collisionResolver.resolve(particle, otherParticle)

                        particle.updateDynamics(dt = dt, newVelocity = resolution.firstParticleNewVelocity)
                        otherParticle.updateDynamics(dt = dt, newVelocity = resolution.secondParticleNewVelocity)
                    }
                }
            }
        }

    }

    private fun Particle.bounceOfXWalls() {
        val xBounds = particleXBounds[this] ?: error("No bounds defined for a particle")
        when {
            x <= xBounds.start -> {
                if (velocity.x < 0) {
                    flipXVelocity()
                }
            }

            x >= xBounds.endInclusive -> {
                if (velocity.x > 0) {
                    flipXVelocity()
                }
            }
        }
    }

    private fun Particle.bounceOfYWalls() {
        val yBounds = particleYBounds[this] ?: error("No bounds defined for a particle")
        when {
            y <= yBounds.start -> {
                if (velocity.y < 0) {
                    flipYVelocity()
                }
            }
            y >= yBounds.endInclusive -> {
                if (velocity.y > 0) {
                    flipYVelocity()
                }
            }
        }
    }
}