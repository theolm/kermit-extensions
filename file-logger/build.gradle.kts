import com.vanniktech.maven.publish.SonatypeHost
import config.Config

plugins {
    id("kmm-module-setup")
    id("publish-setup")
    id("detekt-setup")
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
            implementation(libs.kotlin.coroutines.core)
        }
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()

    val version = System.getenv("VERSION") ?: Config.libVersion
    coordinates(
        groupId = Config.groupId,
        artifactId = artifactId,
        version = version
    )

    pom {
        name.set("Txt Log Writer")
        description.set("It's a simple Kermit log writer that writes logs to a text file.")
    }
}
