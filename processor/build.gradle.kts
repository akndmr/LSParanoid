plugins {
    kotlin("jvm") version "1.9.24"
    `maven-publish`
    signing
}

dependencies {
    compileOnly(gradleApi())
    implementation(projects.core)
    implementation("com.joom.grip:grip:0.9.1")
    implementation("org.ow2.asm:asm-commons:9.7.1")
}