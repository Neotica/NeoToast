import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.android.lint)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.mavenPublish)
}

kotlin {
    androidLibrary {
        namespace = "id.neotica.toast"
        compileSdk = 36
        minSdk = 24

        withHostTestBuilder {
        }

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    val xcfName = "toastKit"

    iosX64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosSimulatorArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlin.stdlib)
            implementation(compose.ui)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        androidMain.dependencies {  }

        getByName("androidDeviceTest") {
            dependencies {
//                implementation(libs.androidx.runner)
//                implementation(libs.androidx.core)
                implementation(libs.androidx.testExt.junit)
            }
        }

        iosMain.dependencies {  }

        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
    }

}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()

    coordinates(
        groupId = "id.neotica",
        artifactId = "toast",
        version = "0.1.3"
    )

    pom {
        name = "NeoToast"
        description = "NeoToast is a Compose MultiPlatform library that gives you toast implementation across Android, iOS, and Desktop JVM. Curated and built with love from the team at Neotica."
        inceptionYear = "2025"
        url = "https://github.com/laetuz/NeoToast"
        licenses {
            license {
                name = "The MIT License"
                url = "https://opensource.org/licenses/MIT"
                distribution = "https://opensource.org/licenses/MIT"
            }
        }
        developers {
            developer {
                id = "laetuz"
                name = "Ryo Martin"
                url = "https://github.com/laetuz/"
            }
        }
        scm {
            url = "https://github.com/laetuz/NeoToast"
            connection = "scm:git:git://github.com/laetuz/NeoToast.git"
            developerConnection = "scm:git:ssh://git@github.com/laetuz/NeoToast.git"
        }
    }
}