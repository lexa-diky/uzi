plugins {
    id("io.github.lexa-diky.uzi.base")
    kotlin("plugin.serialization")
}

dependencies {
    api(projects.engine)
    implementation(libs.kotlin.serialization.core)
}
