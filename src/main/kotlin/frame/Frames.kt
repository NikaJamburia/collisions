package ge.nika.frame

import ge.nika.math.Vec
import ge.nika.screenHeight
import ge.nika.screenWidth

val twoBallsOneBoxFrame = FrameElements(
    container = Container(
        position = Vec(100.0, 100.0),
        width = screenWidth - 200,
        height = screenHeight - 200,
        fillColor = "lightgreen"
    ),
    particles = listOf(
        Particle(
            radius = 25.0,
            color = "red",
            startingPosition = Vec(x = 125.0, y = screenHeight / 2),
            startingVelocity = Vec(x = 500.0, y = 0.0),
            mass = 1.0,
        ),
        Particle(
            radius = 50.0,
            startingPosition = Vec(x = screenWidth - 250.0, screenHeight / 2),
            startingVelocity = Vec(x = -500.0, y = 0.0),
            mass = 1.0,
        ),
    )
)