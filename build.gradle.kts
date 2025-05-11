plugins {
    kotlin("jvm") version "2.1.20"
    application
}

group = "net.taobits"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.9.1")
    testImplementation("io.kotest:kotest-framework-datatest:5.9.1")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
    sourceSets {
        test {
            languageSettings {
                optIn("io.kotest.common.ExperimentalKotest")
            }
        }
    }
}

application {
    mainClass.set("MainKt")
}