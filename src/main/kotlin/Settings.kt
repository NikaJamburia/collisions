package ge.nika

import ge.nika.math.Vec

val screenWidth = 1000.0
val screenHeight = 800.0
val fps = 60.0

val particles = listOf(
    Particle(
        radius = 50.0,
        startingPosition = Vec(x = screenWidth / 2, y = 100.0),
        startingVelocity = Vec(x = 200.0, y = 0.0)
    )
)