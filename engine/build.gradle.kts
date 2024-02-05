plugins {
    id("io.github.lexa-diky.uzi.base")
    kotlin("plugin.serialization")
}

dependencies {
    implementation(libs.kotlin.coroutines)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.kotlin.serialization.core)
    implementation(libs.kotlin.datetime)

    testImplementation(libs.kotlin.serialization.json)
    implementation(kotlin("test"))
}
