rootProject.name = "CloudNetMicrotusBootstrap"

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://eldonexus.de/repository/maven-public/")
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            version("microtus", "1.4.2")
            version("publishdata", "1.4.0")

            library("microtus-bom", "net.onelitefeather.microtus", "bom").versionRef("microtus")
            library("microtus-core", "net.onelitefeather.microtus", "Microtus").withoutVersion()

            plugin("publishdata", "de.chojo.publishdata").versionRef("publishdata")

        }
    }
}