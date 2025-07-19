plugins {
    alias(libs.plugins.mynasadailypics.kotlinMultiplatformLibrary)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.sqldelight.driver)
        }

        commonMain.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlinx.coroutines.test)
            implementation(libs.sqldelight.runtime)
            implementation(libs.sqldelight.coroutines.extensions)
        }

        nativeMain.dependencies {
            implementation(libs.sqldelight.native.driver)
        }

        desktopMain.dependencies {
            implementation(libs.sqldelight.driver)
            implementation(libs.kotlinx.coroutinesSwing)
        }
    }
}
