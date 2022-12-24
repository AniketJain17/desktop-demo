
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
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
    implementation(compose.desktop.currentOs)
    implementation(kotlin("stdlib-jdk8"))
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

repositories {
    mavenCentral()
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

sqldelight {
    database("testDB") {
        packageName = "com.desktop-template"
        sourceFolders = listOf("sqldelight")
    }
}