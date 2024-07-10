package ge.nika.collision

import ge.nika.math.Vec

data class CollisionResolution(
    val firstParticleNewVelocity: Vec,
    val secondParticleNewVelocity: Vec,
)