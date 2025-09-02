
import com.android.build.gradle.tasks.GenerateBuildConfig

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.download)
    alias(libs.plugins.detekt)
    alias(libs.plugins.dependencyAnalysis)
    alias(libs.plugins.vanniktechPublishing)
}

kotlin {
    jvmToolchain(JavaVersion.VERSION_17.majorVersion.toInt())
    compilerOptions {
        optIn.add("kotlin.time.ExperimentalTime")
        optIn.add("androidx.compose.material3.ExperimentalMaterial3Api")
        optIn.add("androidx.compose.material.ExperimentalMaterialApi")
        optIn.add("androidx.compose.ui.ExperimentalComposeUiApi")
        optIn.add("androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi")
        freeCompilerArgs.addAll(
            "-module-name", Pom.LIBRARY_ARTIFACT_ID,
        )
    }
}

android {
    namespace = "org.dbtools.android.commons.ui"

    compileSdk = AndroidSdk.COMPILE

    defaultConfig {
        minSdk = AndroidSdk.MIN
        targetSdk = AndroidSdk.TARGET
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = true
    }

    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs = listOf(
            "-module-name", Pom.LIBRARY_ARTIFACT_ID,
            "-opt-in=kotlin.RequiresOptIn",
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
            "-opt-in=androidx.compose.material.ExperimentalMaterialApi",
            "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
            "-opt-in=androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi",
        )
    }

    buildFeatures {
        compose = true
    }

    lint {
        abortOnError = true
        disable.addAll(listOf("InvalidPackage"))
    }
}

// This prevents a BuildConfig from being created.
tasks.withType<GenerateBuildConfig> {
    isEnabled = false
}

dependencies {
    // Android
    coreLibraryDesugaring(libs.android.desugar)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)
    implementation(libs.androidx.lifecycle.compose)
    implementation(libs.androidx.lifecycle.process)
    compileOnly(libs.androidx.datastorePrefs)

    // Compose
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.material3)
    implementation(libs.compose.material3.windowsize)

    // Navigation
    implementation(libs.androidx.navigation.runtime)

    // Media
    api(libs.androidx.media3.exoplayer)

    // Code
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.time)

    // Logging
    implementation(libs.kermit)

    // Image
    implementation(libs.coil.base)

    // Test (Unit)
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.engine)
    testRuntimeOnly(libs.junit.platform.launcher)
    testImplementation(libs.mockK)
    testImplementation(libs.assertk)
}

// ===== TEST TASKS =====

// create JUnit reports
tasks.withType<Test> {
    useJUnitPlatform()
}

// ===== Detekt =====

// download detekt config file
tasks.register<de.undercouch.gradle.tasks.download.Download>("downloadDetektConfig") {
    download {
        onlyIf { !file("build/config/detektConfig.yml").exists() }
        src("https://raw.githubusercontent.com/ICSEng/AndroidPublic/main/detekt/detektConfig-20231101.yml")
        dest("build/config/detektConfig.yml")
    }
}

// make sure when running detekt, the config file is downloaded
tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    // Target version of the generated JVM bytecode. It is used for type resolution.
    this.jvmTarget = "17"
    dependsOn("downloadDetektConfig")
}

// ./gradlew detekt
detekt {
    allRules = true // fail build on any finding
    buildUponDefaultConfig = true // preconfigure defaults
    config = files("$projectDir/build/config/detektConfig.yml") // point to your custom config defining rules to run, overwriting default behavior
//    baseline = file("$projectDir/config/baseline.xml") // a way of suppressing issues before introducing detekt
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    // ignore ImageVector files
    exclude("**/ui/compose/icons/**")

    reports {
        html.required.set(true) // observe findings in your browser with structure and code snippets
        xml.required.set(true) // checkstyle like format mainly for integrations like Jenkins
        txt.required.set(true) // similar to the console output, contains issue signature to manually edit baseline files
    }
}

// ===== Maven Deploy =====

// ./gradlew clean build check publishToMavenLocal
// ./gradlew clean build check publishToMavenCentral
mavenPublishing {
    val version: String by project
    coordinates("org.dbtools", "android-commons-ui", version)
    publishToMavenCentral()
    signAllPublications()

    pom {
        name.set(Pom.LIBRARY_NAME)
        description.set(Pom.POM_DESCRIPTION)
        url.set(Pom.URL)
        licenses {
            license {
                name.set(Pom.LICENCE_NAME)
                url.set(Pom.LICENCE_URL)
                distribution.set(Pom.LICENCE_DIST)
            }
        }
        developers {
            developer {
                id.set(Pom.DEVELOPER_ID)
                name.set(Pom.DEVELOPER_NAME)
            }
        }
        scm {
            url.set(Pom.SCM_URL)
            connection.set(Pom.SCM_CONNECTION)
            developerConnection.set(Pom.SCM_DEV_CONNECTION)
        }
    }
}
