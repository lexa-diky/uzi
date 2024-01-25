enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    plugins {
        id("org.jetbrains.kotlin.jvm") version "1.9.21"
        id("org.jetbrains.kotlin.plugin.serialization") version "1.9.21"

    }
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

includeBuild("./included-build")
include(":agent", ":controller", "engine")

rootProject.name = "uzi"

