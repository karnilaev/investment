
plugins {
    id("org.siouan.frontend-jdk11") version "5.0.1"
    id("java")
}

group = "com.karnilaev.investment"
version = "0.0.1-SNAPSHOT"

java {
    targetCompatibility = JavaVersion.VERSION_11
}

buildscript {
    repositories {
        mavenCentral()
    }
}

frontend {
    nodeVersion.set("15.0.1")
    cleanScript.set("run clean")
    installScript.set("install")
    assembleScript.set("run build")
}

tasks.named("jar", Jar::class) {
    dependsOn("assembleFrontend")
    from("$buildDir/dist")
    into("static")
}