plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(29)

    defaultConfig {
        // Android version targets
        minSdkVersion(21)
        targetSdkVersion(29)

        // Release version
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        // Use Java 1.8 features
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
//        jvmTarget = compileOptions.targetCompatibility.toString()
    }

    buildTypes {
        getByName("release") {
//            minifyEnabled = false
        }

        getByName("debug") {
            // Use different application id to run release and debug at the same time
            applicationIdSuffix = ".debug"
        }
    }
}

dependencies {
    // Jellyfin
    val jellyfinApiclientVersion= "master-SNAPSHOT"
    implementation("com.github.jellyfin.jellyfin-apiclient-java:android:$jellyfinApiclientVersion")

	// Android
    implementation(kotlin("stdlib-jdk8"))
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.core:core-ktx:1.2.0")
	implementation("androidx.webkit:webkit:1.1.0")

    // Testing
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}
