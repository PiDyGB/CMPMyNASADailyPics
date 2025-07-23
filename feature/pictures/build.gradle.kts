import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType
import org.jetbrains.kotlin.konan.target.Family

plugins {
    alias(libs.plugins.mynasadailypics.kotlinComposeMultiplatformLibrary)
    alias(libs.plugins.mynasadailypics.kotlinxSerialization)
    alias(libs.plugins.mynasadailypics.kotlinComposeMultiplatformTest)
}

kotlin {

    targets.withType<KotlinNativeTarget>().configureEach {
        if (konanTarget.family == Family.IOS) {
            binaries.getTest(NativeBuildType.DEBUG).linkerOpts.add("-lsqlite3")
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.common)
            implementation(projects.core.ui)
            implementation(projects.core.data)
            implementation(projects.core.model)

            implementation(libs.material.icons.extended)

            implementation(libs.bundles.coil)
            implementation(libs.androidx.navigation.compose)
        }
    }
}