import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion

plugins {
    id("com.android.library")
   id("org.jetbrains.kotlin.android")
//    id("jacoco")
    id("maven-publish")
//    id("com.github.kt3k.coveralls")
//    id("signing")
//    id("org.jetbrains.dokka")


}




android {
    namespace = "com.example.psa_trakcer"
    compileSdk = 34

    useLibrary("android.test.base")

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {


        debug {
            buildConfigField ("String", "TRACKER_LABEL", "\"andr-$project.version\"")
            buildConfigField ("String", "TRACKER_VERSION", "\"$project.version\"")
            isTestCoverageEnabled = true
        }
        release {
            buildConfigField("String", "TRACKER_LABEL", "\"andr-$project.version\"")
            buildConfigField("String", "TRACKER_VERSION", "\"$project.version\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }




    buildFeatures {
        buildConfig = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

publishing {
    publications{
        register<MavenPublication>("release"){
            afterEvaluate{
                from(components["release"])
            }
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("com.google.android.datatransport:transport-runtime:3.3.0")
    implementation("androidx.lifecycle:lifecycle-process:2.8.4")
    implementation("com.google.android.gms:play-services-basement:18.4.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")


    // OkHttp3 dependency
    implementation ("com.squareup.okhttp3:okhttp:4.11.0")
    implementation ("com.android.installreferrer:installreferrer:2.2")


    // Test dependencies
    testImplementation("junit:junit:4.13.2")
    testImplementation("androidx.lifecycle:lifecycle-extensions:${project.getKotlinPluginVersion()}")
    testImplementation("com.android.installreferrer:installreferrer:2.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation("com.google.android.gms:play-services-analytics:18.0.3")
    androidTestImplementation("com.squareup.okhttp3:mockwebserver:4.7.2")
}