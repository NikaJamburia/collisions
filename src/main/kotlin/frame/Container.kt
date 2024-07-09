package ge.nika.frame

import ge.nika.math.Vec
import ge.nika.screenFillColor
import ge.nika.screenHeight
import ge.nika.screenWidth

data class Container(
    val position: Vec,
    val width: Double,
    val height: Double,
    val fillColor: String,
) {
    companion object {
        fun wholeScreen(): Container {
            return Container(
                position = Vec(0.0, 0.0),
                width = screenWidth,
                height = screenHeight,
                fillColor = screenFillColor,
            )
        }
    }

    fun getXBounds(): ClosedRange<Double> = position.x..width + position.x
    fun getYBounds(): ClosedRange<Double> = position.y..height + position.y
}