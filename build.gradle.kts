plugins {
    java
    application
    id("io.github.goooler.shadow") version "8.1.7"
    alias(libs.plugins.publishdata)
    `maven-publish`
}

group = "net.onelitefeather.microtus.cloudnet"
version = "1.1.2"

repositories {
    mavenCentral()
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
    maven("https://s01.oss.sonatype.org/content/groups/staging/")
    maven("https://jitpack.io")
}

configurations.all {
    resolutionStrategy.cacheDynamicVersionsFor(1, "minutes")
}

dependencies {
    implementation(platform(libs.microtus.bom))
    implementation(libs.microtus.core)
    implementation("net.kyori:adventure-text-minimessage:4.17.0")


}

application {
    mainClass.set("net.onelitefeather.microtus.cloudnet.Bootstrap")
}

tasks {
    shadowJar {
        archiveVersion.set("")
        archiveClassifier.set("")
        archiveBaseName.set("application")
    }
}

publishData {
    addBuildData()
    useEldoNexusRepos(true)
    publishTask("shadowJar")
}

publishing {
    publications.create<MavenPublication>("maven") {
        // Configure our maven publication
        publishData.configurePublication(this)
    }

    repositories {
        // We add EldoNexus as our repository. The used url is defined by the publish data.
        maven {
            authentication {
                credentials(PasswordCredentials::class) {
                    // Those credentials need to be set under "Settings -> Secrets -> Actions" in your repository
                    username = System.getenv("ELDO_USERNAME")
                    password = System.getenv("ELDO_PASSWORD")
                }
            }

            name = "EldoNexus"
            setUrl(publishData.getRepository())
        }
    }
}
