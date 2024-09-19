plugins {
    java
    idea
}

group = "org.stolenstars"
version = "1.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:24.0.0")
}

tasks.withType<JavaCompile> {
    options.encoding = Charsets.UTF_8.name()
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "org.stolenstars.lcpp.LethalCompanyProjectPacker"
    }
}