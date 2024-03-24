import config.Config

plugins {
    id("kmm-module-setup")
}

android {
    namespace = Config.applicationId
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kermit)
        }
    }
}