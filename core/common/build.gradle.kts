plugins {
    alias(libs.plugins.mynasadailypics.kotlinMultiplatformLibrary)
}

kotlin {
    sourceSets {
        val desktopMain by getting
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
        }
        desktopMain.dependencies {
            implementation(libs.kotlinx.coroutinesSwing)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlinx.coroutines.test)
        }
    }
}
