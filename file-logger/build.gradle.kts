import com.vanniktech.maven.publish.SonatypeHost
import config.Config

plugins {
    id("kmm-module-setup")
    id("publish-setup")
}

val artifactId = "txtlogwriter"

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

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()
    coordinates(Config.groupId, artifactId, Config.libVersion)

    pom {
        name.set("Txt Log Writer")
        description.set("It's a simple Kermit log writer that writes logs to a text file.")
    }
}