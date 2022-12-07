import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    application
}

group = "uk.dioxic"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("AppKt")
}

val mongoVersion = "4.8.0"

repositories {
    mavenCentral()
}

dependencies {
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("com.github.ajalt.clikt:clikt:3.5.0")
    implementation("org.slf4j:slf4j-api:2.0.5")
    implementation(platform("org.apache.logging.log4j:log4j-bom:2.19.0"))
    implementation("org.apache.logging.log4j:log4j-api")
    implementation("org.apache.logging.log4j:log4j-core")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl")
    implementation("org.mongodb:mongodb-driver-sync:$mongoVersion")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}