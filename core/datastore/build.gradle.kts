plugins {
    alias(libs.plugins.mynasadailypics.kotlinMultiplatformLibrary)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.model)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.test)
            implementation(libs.turbine)
        }
    }
}
