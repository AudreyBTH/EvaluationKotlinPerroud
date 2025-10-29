@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {

    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "EvaluationKotlinPerroud"
include(":app")

include(":core:ui")
include(":core:data")
include(":core:domain")
include(":core:system")

include(":features:featureModel:data")
include(":features:featureModel:domain")
include(":features:featureModel:ui")