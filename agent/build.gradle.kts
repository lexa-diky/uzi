plugins {
    id("io.github.lexa-diky.uzi.base")
}

dependencies {
    implementation(libs.ktor.server.engine.netty)
    implementation(libs.logback.classic)
}