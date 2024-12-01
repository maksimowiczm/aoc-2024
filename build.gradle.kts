plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = "com.maksimowiczm.aoc2024"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation(libs.testing.kotest)
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}