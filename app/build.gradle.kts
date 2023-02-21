plugins {
    id("groline.uploader.kotlin-application-conventions")
}

dependencies {
    implementation("com.opencsv:opencsv:5.3")
}

application {
    // Define the main class for the application.
    mainClass.set("groline.uploader.app.Uploader")
}
