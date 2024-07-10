package ge.nika.collision

import ge.nika.frame.Particle

interface CollisionResolver {
    fun resolve(first: Particle, second: Particle): CollisionResolution
}