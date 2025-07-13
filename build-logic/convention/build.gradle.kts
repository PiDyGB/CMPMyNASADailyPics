import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.pidygb.mynasadailypics.buildlogic"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {

        register("kotlinMultiplatformLibrary") {
            id = libs.plugins.mynasadailypics.kotlinMultiplatformLibrary.get().pluginId
            implementationClass = "com.pidygb.mynasadailypics.KotlinMultiplatformLibraryConventionPlugin"
        }

        register("kotlinComposeMultiplatformLibrary") {
            id = libs.plugins.mynasadailypics.kotlinComposeMultiplatformLibrary.get().pluginId
            implementationClass = "com.pidygb.mynasadailypics.KotlinComposeMultiplatformLibraryConventionPlugin"
        }


    }
}