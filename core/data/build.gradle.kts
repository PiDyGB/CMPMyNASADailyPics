plugins {
    alias(libs.plugins.mynasadailypics.kotlinMultiplatformLibrary)
}

kotlin {
    sourceSets {
        val desktopMain by getting
        commonMain.dependencies {
            implementation(projects.core.common)
            implementation(projects.core.network)
            implementation(projects.core.model)
            implementation(projects.core.database)
            implementation(libs.kotlinx.coroutines.core)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        desktopMain.dependencies {
            implementation(libs.kotlinx.coroutinesSwing)
        }
    }
}
