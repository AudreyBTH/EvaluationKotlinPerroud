import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

}

android {
    namespace = "com.example.evaluationkotlinperroud.features.anime.api"
    compileSdk = 36

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.fromTarget("17")
        }
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources.excludes += setOf(
            "dump_syms/linux/**",
            "META-INF/*.kotlin_module"
        )
    }
}

dependencies {


    implementation(project(":features:anime:domain"))
    implementation(project(":features:anime:ui"))

    implementation(project.dependencies.platform(libs.koin.bom))
    implementation(libs.bundles.koin)
}