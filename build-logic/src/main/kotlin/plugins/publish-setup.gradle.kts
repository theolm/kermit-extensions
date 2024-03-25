plugins {
    id("com.vanniktech.maven.publish")
}

mavenPublishing {
    pom {
        inceptionYear.set("2024")
        url.set("https://github.com/theolm/kermit-extensions")
        licenses {
            license {
                name.set("WTFPL - Do What The F*ck You Want To Public License, Version 2.0")
                url.set("http://www.wtfpl.net/")
                distribution.set("http://www.wtfpl.net/")
            }
        }
        developers {
            developer {
                id.set("theolm")
                name.set("Theodoro Loureiro Mota")
                url.set("https://github.com/theolm/")
            }
        }
        scm {
            url.set("https://github.com/theolm/kermit-extensions/")
            connection.set("scm:git:git://github.com/theolm/kermit-extensions.git")
            developerConnection.set("scm:git:ssh://git@github.com/theolm/kermit-extensions.git")
        }
    }
}