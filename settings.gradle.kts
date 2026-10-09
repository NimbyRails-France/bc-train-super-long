pluginManagement {
    val sdk = providers.gradleProperty("nrfSdkDir")
        .orElse(providers.environmentVariable("NRF_KOTLIN_SDK"))
        .orNull ?: error("Configure nrfSdkDir with the Kotlin SDK kit directory.")
    repositories {
        maven { url = uri(file(sdk).resolve("gradle-repository")) }
        gradlePluginPortal()
        mavenCentral()
    }
    // The plugin, API and native bridge must come from the same SDK kit.
    val metadata = groovy.json.JsonSlurper()
        .parseText(file(sdk).resolve("sdk.json").readText().removePrefix("\uFEFF")) as Map<*, *>
    plugins { id("fr.nimbyrails.mod") version (metadata["gradlePluginVersion"] as String) }
}
dependencyResolutionManagement { repositories { mavenCentral() } }
rootProject.name = "bc-train-super-long"
