plugins {
    java
    kotlin("jvm") version "1.4.21"
}

group = "net.eduard"
version = "1.0-SNAPSHOT"
repositories {
    mavenLocal()
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://jitpack.io/")
}
dependencies {
    compileOnly(kotlin("stdlib"))
    compileOnly("com.github.MilkBowl:VaultAPI:1.7")
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
    compileOnly("net.eduard:eduardapi:1.0-SNAPSHOT")
}
java.targetCompatibility = JavaVersion.VERSION_1_8
tasks.withType<Jar>{
    destinationDir = file("E:\\Tudo\\Minecraft - Server\\Servidor Teste\\plugins\\")
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
tasks.withType<JavaCompile>{
    options.encoding = "UTF-8"
}