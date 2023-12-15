val apolloVersion = "4.0.0-beta.4"
val hiltVersion = "2.49"

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.apollographql.apollo3").version("4.0.0-beta.4")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

apollo {
    service("service") {
        packageName.set("com.graph")
    }
}

android {
    namespace = "com.graph.apollo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.graph.apollo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    val paging_version = "3.2.1"
    val room_version = "2.6.1"
    val voyagerVersion = "1.0.0"

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("com.apollographql.apollo3:apollo-runtime:$apolloVersion")


    // Dagger
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    ksp ("com.google.dagger:hilt-android-compiler:$hiltVersion")
    ksp ("androidx.hilt:hilt-compiler:1.1.0")
    implementation ("androidx.hilt:hilt-navigation-compose:1.1.0")

    // Coil
    implementation("io.coil-kt:coil-compose:2.5.0")

    //Paging
    implementation ("androidx.paging:paging-compose:$paging_version")
    implementation ("androidx.paging:paging-runtime-ktx:$paging_version")

    // Room
    implementation ("androidx.room:room-ktx:$room_version")
    ksp ("androidx.room:room-compiler:$room_version")
    implementation ("androidx.room:room-paging:$room_version")

    //Voyager Nav
    implementation("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")
}