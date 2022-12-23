
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
//object SqlDelight {
//    const val runtime = "com.squareup.sqldelight:runtime:$"
//    const val android = "com.squareup.sqldelight:android-driver:$"
//    const val native = "com.squareup.sqldelight:native-driver:$"
//}

buildscript {
    repositories {
        google()
        mavenCentral()

    }
    dependencies {
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.3")
    }
}

apply(plugin = "com.squareup.sqldelight")

plugins {
kotlin("jvm")
id("org.jetbrains.compose")
id("com.squareup.sqldelight")
}

dependencies {
    // SQLDelight
    implementation("com.squareup.sqldelight:runtime:1.5.3")
//    implementation("com.squareup.sqldelight:android-driver:1.5.3")
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "KotlinJvmComposeDesktopApplication"
            packageVersion = "1.0.0"
        }
    }
}

//sqldelight {
//    database("testDB") {
//        packageName = "com.desktop_template"
//        sourceFolders = listOf("sqldelight")
//    }
//}
