package ge.nika.javafx

import ge.nika.*
import javafx.animation.AnimationTimer
import javafx.scene.shape.Circle

class JavaFxTimer(
    private val displayCircles: Map<Particle, Circle>
) : AnimationTimer() {
    private var lastUpdate = 0L
    private val nanosecondsPerFrame = 1_000_000_000 / fps


    override fun handle(now: Long) {
        if (now - lastUpdate >= nanosecondsPerFrame) {
            displayCircles.forEach { (particle, display) ->
                particle.updateDynamics(1 / fps)
                display.centerX = particle.x
                display.centerY = particle.y

                if (particle.isOutOfX()) {
                    particle.flipXVelocity()
                }
                if (particle.isOutOfY()) {
                    particle.flipYVelocity(damping = 1.0)
                }
            }
            lastUpdate = now
        }
    }

    private fun Particle.isOutOfX(): Boolean {
        return x !in radius..screenWidth - radius
    }

    private fun Particle.isOutOfY(): Boolean {
        return y !in radius..screenHeight - radius
    }
}