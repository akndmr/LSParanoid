plugins {
    id("org.jetbrains.kotlin.jvm") version "2.0.21"
    `maven-publish`
    signing
}

dependencies {
    compileOnly(gradleApi())
    implementation(projects.core)
    implementation("com.joom.grip:grip:0.9.1")
    implementation("org.ow2.asm:asm-commons:9.7.1")
}

publish {
    githubRepo = "LSPosed/LSParanoid"
    publications {
        register<MavenPublication>(rootProject.name) {
            artifactId = project.name
            group = group
            version = version
            from(components.getByName("java"))
            pom {
                name = project.name
                description = "String obfuscator for Android applications"
                url = "https://github.com/LSPosed/LSParanoid"
                licenses {
                    license {
                        name = "Apache License 2.0"
                        url = "https://github.com/LSPosed/LSParanoid/blob/master/LICENSE.txt"
                    }
                }
                developers {
                    developer {
                        name = "LSPosed"
                        url = "https://lsposed.org"
                    }
                }
                scm {
                    connection = "scm:git:https://github.com/LSPosed/LSParanoid.git"
                    url = "https://github.com/LSPosed/LSParanoid"
                }
            }
        }
    }
}
