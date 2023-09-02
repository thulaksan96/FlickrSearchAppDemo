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

rootProject.name = "FlickrSearchAppDemo"
include(":app")
include(":common:networking")
include(":feature-api:flickr_search")
include(":feature-ui:image_search_ui")
