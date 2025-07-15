plugins {
    alias(libs.plugins.mynasadailypics.kotlinMultiplatformLibrary)
    alias(libs.plugins.mynasadailypics.kotlinComposeMultiplatformLibrary)
    alias(libs.plugins.mynasadailypics.kotlinxSerialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.common)
            implementation(projects.core.ui)
            implementation(projects.core.data)
            implementation(projects.core.model)

            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.compose.viewmodel.navigation)
            implementation(libs.bundles.coil)
            implementation(libs.androidx.navigation.compose)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.test)
            implementation(libs.turbine)
            implementation(libs.mockk)
            implementation(libs.kotlinx.coroutines.test)
        }

    }
}