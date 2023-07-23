import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.ajoberstar.grgit.Grgit
import java.util.*

plugins {
    id("java")
    application
    id("org.ajoberstar.grgit") version "5.2.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

var baseVersion by extra("1.0.0")
var versionExtension by extra("")
var snapshot by extra("-SNAPSHOT")

group = "net.onelitefeather.microtus.cloudnet"
ext {
    val git: Grgit = Grgit.open {
        dir = File("$rootDir/.git")
    }
    val revision = git.head().abbreviatedId
    if (snapshot.isNotEmpty()) {
        versionExtension = "%s+%s".format(Locale.ROOT, snapshot, revision)
    } else {
        versionExtension = ""
    }

}

version = "%s%s".format(Locale.ROOT, baseVersion, versionExtension)

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://jitpack.io")
}

dependencies {
    implementation("net.onelitefeather.microtus:Minestom:1.1.0-SNAPSHOT+9284d26")
}

application {
    mainClass.set("net.onelitefeather.microtus.cloudnet.Bootstrap")
}

tasks {
    create("createServerJar", ShadowJar::class) {
        archiveVersion.set("")
    }
}