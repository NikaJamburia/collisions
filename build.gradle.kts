plugins {
    kotlin("jvm") version "1.9.23"
    id("org.openjfx.javafxplugin") version "0.1.0"
    id("application")
}

group = "ge.nika"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val javafxVersion = "22"

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.openjfx:javafx-base:$javafxVersion")
    implementation("org.openjfx:javafx-controls:$javafxVersion")
    implementation("org.openjfx:javafx-graphics:$javafxVersion")
//    implementation("org.openjfx:javafx-animation:$javafxVersion")

}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

tasks.withType<JavaExec> {
    systemProperty("prism.lcdtext", "false")
    systemProperty("prism.text", "t2k")
    jvmArgs = listOf("--add-modules", "javafx.controls")
}

javafx {
    version = javafxVersion
    modules = listOf("javafx.base", "javafx.controls", "javafx.graphics")
}

application {
    mainClass = "MainKt"
}