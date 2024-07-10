package ge.nika.collision

import ge.nika.frame.Particle

interface CollisionDetector {
    fun detect(particles: List<Particle>): List<Pair<Particle, Particle>>
}