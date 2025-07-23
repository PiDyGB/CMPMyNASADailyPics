plugins {
    alias(libs.plugins.mynasadailypics.kotlinComposeMultiplatformLibrary)
    alias(libs.plugins.mynasadailypics.kotlinComposeMultiplatformTest)
}

kotlin {
    sourceSets {
        val desktopMain by getting
        androidMain.dependencies {
            // Ktor client dependency required for Coil
            implementation(libs.ktor.client.okhttp)
        }
        commonMain.dependencies {
            implementation(libs.bundles.coil)
            implementation(libs.kotlinx.coroutines.core)
        }
        nativeMain.dependencies {
            // Ktor client dependency required for iOS
            implementation(libs.ktor.client.darwin)
        }
        desktopMain.dependencies {
            // Ktor client dependency required for JVM/Desktop
            implementation(libs.ktor.client.okhttp)
            implementation(libs.kotlinx.coroutinesSwing)
        }
    }
}
