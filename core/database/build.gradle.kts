plugins {
    alias(libs.plugins.mynasadailypics.kotlinMultiplatformLibrary)
    alias(libs.plugins.sqldelight)
}

kotlin {
    sourceSets {
        val desktopMain by getting
        val androidUnitTest by getting
        androidMain.dependencies {
            implementation(libs.sqldelight.android.driver)
        }
        androidUnitTest.dependencies {
            implementation(libs.sqldelight.driver)
        }

        commonMain.dependencies {
            implementation(projects.core.model)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.sqldelight.runtime)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.sqldelight.coroutines.extensions)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlinx.coroutines.test)
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

sqldelight {
    databases {
        create("PicturesDatabase") {
            packageName.set("com.pidygb.mynasadailypics.core.database")
        }
    }
}