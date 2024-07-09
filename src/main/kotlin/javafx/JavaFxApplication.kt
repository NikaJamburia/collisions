package ge.nika.javafx

import ge.nika.*
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.Background
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import javafx.scene.shape.Circle
import javafx.stage.Stage

class JavaFxApplication : Application() {
    override fun start(stage: Stage?) {
        val displayCircles = particles.associateWith { it.toJavaFxCircle() }

        val pane = Pane()
        pane.background = Background.fill(Paint.valueOf("black"))
        pane.children.addAll(displayCircles.values)

        stage!!.title = "Drawing Shapes with JavaFX"
        stage.scene = Scene(pane, screenWidth, screenHeight)
        stage.isResizable = false
        stage.show()

        val timer = JavaFxTimer(displayCircles)
        timer.start()
    }

    private fun Particle.toJavaFxCircle(color: Color = Color.LIGHTGREEN): Circle = Circle(
        position.x,
        position.y,
        radius,
    ).also { it.fill = color }
}