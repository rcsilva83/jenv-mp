plugins {
    kotlin("multiplatform") version "1.9.21"
}

group = "jenv"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    listOf(
        linuxX64(),
        mingwX64(),
        macosX64()
    ).forEach {
        it.apply {
            binaries {
                executable {
                    entryPoint = "main"
                }
            }
        }
    }
    sourceSets {
        val clickVersion = "4.2.2"
        val okioVersion = "3.9.0"
        val commonMain by getting {
            dependencies {
                implementation("com.github.ajalt.clikt:clikt:$clickVersion")
                implementation("com.squareup.okio:okio:$okioVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation("com.squareup.okio:okio-fakefilesystem:$okioVersion")
                implementation(kotlin("test"))
            }
        }
    }
}
