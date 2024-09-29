import com.flare.gradle.configuration.FlareExtension
import com.flare.gradle.configuration.FlarePlatformType


buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
    }
    dependencies {
        classpath("com.flare.gradle:gradle-plugin:1.0.0-SNAPSHOT")
    }
}

plugins {
    kotlin("jvm") version "2.0.0"
    id("com.flare.gradle") version "1.0.0-SNAPSHOT"
}

group = "com.flare"
version = "1.0-SNAPSHOT"

configure<FlareExtension> {
    entrypoint = "com.flare.example.ExamplePlugin"
    sdkVersion = "1.0.0-SNAPSHOT"
    platforms = mapOf(
        FlarePlatformType.SPIGOT to "org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT",
        FlarePlatformType.VELOCITY to "com.velocitypowered:velocity-api:4.0.0-SNAPSHOT",
        FlarePlatformType.BUNGEECORD to "net.md-5:bungeecord-api:1.21-R0.1-SNAPSHOT"
    )
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}