package ge.nika.javafx

import ge.nika.*
import ge.nika.frame.FrameElements
import ge.nika.frame.FrameSolver
import ge.nika.frame.Particle
import javafx.animation.AnimationTimer
import javafx.scene.shape.Circle

class JavaFxTimer(
    private val displayCircles: Map<Particle, Circle>,
    private val frameElements: FrameElements,
) : AnimationTimer() {
    private var lastUpdate = 0L
    private val nanosecondsPerFrame = 1_000_000_000 / fps

    private val frameSolver = FrameSolver(
        frameXBounds = frameElements.container.getXBounds(),
        frameYBounds = frameElements.container.getYBounds(),
        particles = displayCircles.keys.toList()
    )

    override fun handle(now: Long) {
        if (now - lastUpdate >= nanosecondsPerFrame) {
            frameSolver.updateParticleDynamics(fps)
            displayCircles.forEach { (particle, display) ->
                display.centerX = particle.x
                display.centerY = particle.y
            }
            lastUpdate = now
        }
    }
}