plugins {
    alias(libs.plugins.kotlin)
    `maven-publish`
    signing
}

dependencies {
    compileOnly(gradleApi())
    implementation(projects.core)
    implementation(libs.grip)
    implementation(libs.asm.common)
}

publish {
    githubRepo = "https://github.com/akndmr/LSParanoid"
    publications {
        register<MavenPublication>(rootProject.name) {
            artifactId = project.name
            group = "com.github.akndmr"
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
