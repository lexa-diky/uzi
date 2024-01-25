plugins {
    id("io.github.lexa-diky.uzi.base")
}

dependencies {
    implementation(projects.agentContract)
    implementation(libs.ktor.server.engine.netty)
    implementation(libs.ktor.server.websockets)
    implementation(libs.ktor.server.contentNegotiation)
    implementation(libs.ktor.server.contentNegotiation.json)
    implementation(libs.logback.classic)
}