import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinBasePlugin


plugins {
    kotlin("jvm") version "1.9.24"
    id("org.lsposed.lsplugin.publish") version "1.1"
}


allprojects {
    group = "org.lsposed.lsparanoid"
    version = "0.6.1"

    plugins.withType(JavaPlugin::class.java) {
        extensions.configure(JavaPluginExtension::class.java) {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
    }

    plugins.withType(KotlinBasePlugin::class.java) {
        extensions.configure(KotlinJvmProjectExtension::class.java) {
            jvmToolchain(17)
        }
    }
}
