plugins {
    kotlin("multiplatform") version "1.9.21"
}

group = "jenv"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    nativeTarget.apply {
        binaries {
            executable {
                entryPoint = "main"
            }
        }
    }
    sourceSets {
        val clickVersion = "4.2.2"
        val okioVersion = "3.9.0"
        val nativeMain by getting {
            dependencies {
                implementation("com.github.ajalt.clikt:clikt:$clickVersion")
                implementation("com.squareup.okio:okio:$okioVersion")
            }
        }
        val nativeTest by getting {
            dependencies {
                implementation("com.squareup.okio:okio-fakefilesystem:$okioVersion")
                implementation(kotlin("test"))
            }
        }
    }
}
