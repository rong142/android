import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id("com.android.application") version "8.0.0" apply false
    id("com.android.library") version "8.0.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.20" apply false
    id("com.google.gms.google-services") version "4.3.15" apply false
    id("com.github.ben-manes.versions") version "0.41.0" apply false
}

allprojects {
    repositories {
        google()
        mavenLocal()
        mavenCentral()
    }
}

fun isNonStable(candidate: ModuleComponentIdentifier): Boolean {
    return listOf("alpha", "beta", "rc", "snapshot").any { keyword ->
        keyword in candidate.version.lowercase()
    }
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate)
    }
}
