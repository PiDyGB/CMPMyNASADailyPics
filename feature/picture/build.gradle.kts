plugins {
    alias(libs.plugins.mynasadailypics.kotlinComposeMultiplatformLibrary)
    alias(libs.plugins.mynasadailypics.kotlinxSerialization)
    alias(libs.plugins.mynasadailypics.kotlinComposeMultiplatformTest)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.model)
            implementation(libs.material.icons.extended)

            implementation(libs.bundles.coil)
            implementation(libs.androidx.navigation.compose)
        }
    }
}