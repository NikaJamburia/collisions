package ge.nika.javafx

import ge.nika.*
import ge.nika.frame.Container
import ge.nika.frame.FrameElements
import ge.nika.frame.Particle
import ge.nika.frame.twoBallsOneBoxFrame
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.Background
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle
import javafx.stage.Stage

class JavaFxApplication : Application() {
    override fun start(stage: Stage?) {

        val frameElements = twoBallsOneBoxFrame

        val displayCircles = frameElements.particles.associateWith { it.toJavaFxCircle() }
        val containerRec = frameElements.container.toJavafxRectangle()

        val pane = Pane()
        pane.background = Background.fill(Paint.valueOf(screenFillColor))
        pane.children.add(containerRec)
        pane.children.addAll(displayCircles.values)

        stage!!.title = "Drawing Shapes with JavaFX"
        stage.scene = Scene(pane, screenWidth, screenHeight)
        stage.isResizable = false
        stage.show()

        val timer = JavaFxTimer(displayCircles, frameElements)
        timer.start()
    }

    private fun Container.toJavafxRectangle(): Rectangle =
        Rectangle(position.x, position.y, width, height).also {
            it.fill = Color.valueOf(fillColor)
        }

    private fun Particle.toJavaFxCircle(): Circle = Circle(
        position.x,
        position.y,
        radius,
    ).also { it.fill = Color.valueOf(color) }
}