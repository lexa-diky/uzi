plugins {
    id("io.github.lexa-diky.uzi.base")
    kotlin("plugin.serialization")
}

dependencies {
    implementation(libs.kotlin.coroutines)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.kotlin.serialization.core)

    testImplementation(libs.kotlin.serialization.json)
    implementation(kotlin("test"))
}
