plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization") version "1.4.10"
}

kotlin {
    android()
    
    listOf(
        iosX64(),
        iosArm64(),
        //iosSimulatorArm64() sure all ios dependencies support this target
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    val koinVersion = "3.1.2"
    val ktorVersion = "1.5.0"
    val serializationVersion = "1.0.0-RC"
    val coroutinesVersion = "1.6.0"

    sourceSets {

        val commonMain by getting {
            dependencies {

                // Network (Ktor)
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")

                // Async (Kotlinx Coroutines)
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

                // Serialization (Kotlinx Serialization)
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")

                // DI (Koin)
                implementation("io.insert-koin:koin-core:$koinVersion")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val androidMain by getting {
            dependencies {

                // Async (Kotlinx Coroutines)
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

                // Network (Ktor)
                implementation("io.ktor:ktor-client-android:$ktorVersion")

                // DI (Koin)
                implementation("io.insert-koin:koin-android:$koinVersion")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        //val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            //iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        //val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            //iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 31
    }
}

