package ge.nika.frame

import ge.nika.math.Vec
import kotlin.math.max
import kotlin.math.min

class FrameBuilder {

    private var container: Container = Container.wholeScreen()
    private val particles: MutableList<Particle> = mutableListOf()

    fun createCenteredContainer(width: Number, height: Number, color: String) {
        check(particles.isEmpty()) {
            "Can not set a container as particles have already been added"
        }
        container = Container.centered(width, height, color)
    }

    fun addParticle(xPosition: Number, yPosition: Number,builderFunc: ParticleBuilder.() -> Unit) {
        val builder = ParticleBuilder()
        builder.builderFunc()

        val xBound = (container.getXBounds().start + builder.radius.toDouble())..(container.getXBounds().endInclusive - builder.radius.toDouble())
        val yBound = (container.getYBounds().start + builder.radius.toDouble())..(container.getYBounds().endInclusive - builder.radius.toDouble())

        builder.position(
            x = xPosition.toDouble().forceToRange(xBound),
            y = yPosition.toDouble().forceToRange(yBound),
        )
        particles.add(builder.build())
    }

    fun particleRow(
        count: Int,
        spacing: Double = 10.0,
        yOffset: Double,
        builderFunc: ParticleBuilder.() -> Unit,
    ) {
        //TODO
    }

    fun build(): FrameElements = FrameElements(container, particles)

    private fun Double.forceToRange(range: ClosedRange<Double>): Double {
        return when {
            this < range.start -> range.start
            this > range.endInclusive -> range.endInclusive
            else -> this
        }
    }
}

class ParticleBuilder {
    var radius: Number = 1.0
    var color: String = "White"
    var mass: Number = 1.0
    private var startingPosition: Vec = Vec(0.0, 0.0)
    private var startingVelocity: Vec = gravity
    private var acceleration: Vec = gravity
    private var printOnUpdate: Boolean = false

    fun debuggable(value: Boolean) {
        printOnUpdate = value
    }

    fun position(x: Number, y: Number) {
        startingPosition = Vec(x.toDouble(), y.toDouble())
    }

    fun movingLeft() {
        startingVelocity = Vec(-500.0, 0.0)
    }

    fun stationary() {
        startingVelocity = Vec(0.0, 0.0)
        acceleration = Vec(0.0, 0.0)
    }

    fun build(): Particle = Particle(
        radius = radius.toDouble(),
        color = color,
        mass = mass.toDouble(),
        startingPosition = startingPosition,
        startingVelocity = startingVelocity,
        printOnUpdate = printOnUpdate,
        acceleration = acceleration,
    )

}

fun buildFrame(builderFunc: FrameBuilder.() -> Unit): FrameElements {
    val builder = FrameBuilder()
    builder.builderFunc()
    return builder.build()
}