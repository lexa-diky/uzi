plugins {
    id("io.github.lexa-diky.uzi.base")
}

dependencies {
    implementation(libs.kotlin.coroutines)
    implementation(libs.ktor.client.okhttp)
    implementation(kotlin("test"))
}
