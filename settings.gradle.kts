pluginManagement {
    repositories {
        mavenLocal()
        google()
        mavenCentral()
        gradlePluginPortal()

//        maven("https://oss.sonatype.org/content/repositories/snapshots")
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenLocal()
        google()
        mavenCentral()

//        maven("https://oss.sonatype.org/content/repositories/snapshots")
    }
}

include(":library")
