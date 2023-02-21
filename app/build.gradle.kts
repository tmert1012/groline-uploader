plugins {
    id("groline.uploader.kotlin-application-conventions")
    java
}

dependencies {
    implementation("com.opencsv:opencsv:5.3")
}

application {
    // Define the main class for the application.
    mainClass.set("groline.uploader.app.Uploader")
}

tasks.withType<Jar> {
    // Otherwise you'll get a "No main manifest attribute" error
    manifest {
        attributes["Main-Class"] = "groline.uploader.app.Uploader"
    }
    // To avoid the duplicate handling strategy error
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    // To add all of the dependencies
    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}
