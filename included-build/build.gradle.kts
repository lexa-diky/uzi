plugins {
    kotlin("jvm") version "1.9.21"
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(17)
}

gradlePlugin {
    plugins {
        create("io.github.lexa-diky.uzi.base") {
            id = name
            implementationClass = "io.github.lexadiky.uzi.build.BasePlugin"
        }
    }
}