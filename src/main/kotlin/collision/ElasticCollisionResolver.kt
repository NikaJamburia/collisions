package ge.nika.collision

import ge.nika.frame.Particle
import ge.nika.math.Vec
import kotlin.math.pow

/**
 * https://en.wikipedia.org/wiki/Elastic_collision
 */
class ElasticCollisionResolver : CollisionResolver {
    override fun resolve(first: Particle, second: Particle): CollisionResolution = CollisionResolution(
        firstParticleNewVelocity = first.calculateVelocityAfterCollisionWith(second),
        secondParticleNewVelocity = second.calculateVelocityAfterCollisionWith(first),
    )

    private fun Particle.calculateVelocityAfterCollisionWith(otherParticle: Particle): Vec {
        val positionDiff = position - otherParticle.position
        val massDiff = 2 * otherParticle.mass / (mass + otherParticle.mass)
        val velocityDiff = velocity - otherParticle.velocity

        return velocity - (
            positionDiff *
            massDiff *
            (velocityDiff.dot(positionDiff) / positionDiff.magnitude().pow(2))
        )
    }
}