plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(project(":annotations"))
    implementation(libs.symbol.processing.api.v20201024)
}