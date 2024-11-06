import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.jvm.tasks.Jar

plugins {
    idea
    id("org.jetbrains.kotlin.jvm") version "1.9.24"
    `java-gradle-plugin`
}


dependencies {
    implementation(projects.core)
    implementation(projects.processor)
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin-api:1.9.24")
    implementation("com.android.tools.build:gradle-api:8.2.2")
}

val generatedDir = File(projectDir, "generated")
val generatedJavaSourcesDir = File(generatedDir, "main/java")

val genTask = tasks.register("generateBuildClass") {
    inputs.property("version", version)
    outputs.dir(generatedDir)
    doLast {
        val buildClassFile =
            File(generatedJavaSourcesDir, "org/lsposed/lsparanoid/plugin/Build.java")
        buildClassFile.parentFile.mkdirs()
        buildClassFile.writeText(
            """
            package org.lsposed.lsparanoid.plugin;
            /**
             * The type Build.
             */
            public class Build {
               /**
                * The constant VERSION.
                */
               public static final String VERSION = "$version";
            }""".trimIndent()
        )
    }
}

sourceSets {
    main {
        java {
            srcDir(generatedJavaSourcesDir)
        }
    }
}

tasks.withType(KotlinCompile::class.java) {
    dependsOn(genTask)
}

tasks.withType(Jar::class.java) {
    dependsOn(genTask)
}

idea {
    module {
        generatedSourceDirs.add(generatedJavaSourcesDir)
    }
}