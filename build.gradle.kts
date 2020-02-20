buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:3.5.3")
        classpath(kotlin("gradle-plugin", "1.3.61"))
    }
}

allprojects {
    repositories {
        jcenter()
        mavenCentral()
        google()
        maven("https://jitpack.io")
    }
}
