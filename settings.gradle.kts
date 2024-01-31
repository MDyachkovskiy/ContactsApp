pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ContactsApp"
include(":app")
include(":core")
include(":remote_data")
include(":contacts_list_screen")
include(":contact_details")
include(":local_data")
