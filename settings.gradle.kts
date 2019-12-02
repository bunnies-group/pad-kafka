pluginManagement {

    plugins {
        id("org.springframework.boot") version "2.2.1.RELEASE"
        id("io.spring.dependency-management") version "1.0.8.RELEASE"

        kotlin("jvm") version "1.3.61"
        kotlin("plugin.spring") version "1.3.61"

        id("com.google.cloud.tools.jib") version "1.8.0"
    }
}

rootProject.name = "kafka-pad"

include("producer", "consumer")
