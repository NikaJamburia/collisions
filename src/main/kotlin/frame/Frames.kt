package ge.nika.frame

import ge.nika.math.Vec
import ge.nika.screenHeight
import ge.nika.screenWidth


val twoBallsOneBoxFrame = buildFrame {
    createCenteredContainer(800,600, "lightgreen")

    addParticle(1000, 1000) {
        radius = 25.0
        color = "red"
        mass = 1.0
        stationary()
        debuggable(true)
    }

//    addParticle(150, 0) {
//        radius = 50.0
//        color = "white"
//        mass = 30.0
//        stationary()
//    }
}