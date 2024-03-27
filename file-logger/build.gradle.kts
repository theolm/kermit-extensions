import config.Config

plugins {
    id("kmm-module-setup")
}

android {
    namespace = Config.applicationId + ".txtlogwriter"
}

kotlin {
    explicitApi()
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.startup)
        }
        commonMain.dependencies {
            implementation(libs.kermit)
            implementation(libs.kotlin.datetime)
        }
    }
}