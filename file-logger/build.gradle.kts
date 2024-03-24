import config.Config

plugins {
    id("kmm-module-setup")
}

android {
    namespace = Config.applicationId + ".txtlogwriter"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kermit)
        }
    }
}