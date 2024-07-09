package ge.nika.frame

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
                        resolveCollision(particle, otherParticle)
                    }
                }
            }
        }
    }

    private fun resolveCollision(particle: Particle, otherParticle: Particle) {
        particle.flipXVelocity()
        particle.flipYVelocity()

        otherParticle.flipXVelocity()
        otherParticle.flipYVelocity()
    }

    private fun Particle.isOutOfX(): Boolean = x !in xBoundsFor(this)
    private fun Particle.isOutOfY(): Boolean = y !in yBoundsFor(this)

    private fun xBoundsFor(particle: Particle): ClosedRange<Double> =
        (frameXBounds.start + particle.radius) .. (frameXBounds.endInclusive - particle.radius)

    private fun yBoundsFor(particle: Particle): ClosedRange<Double> =
        (frameYBounds.start + particle.radius) .. (frameYBounds.endInclusive - particle.radius)
}