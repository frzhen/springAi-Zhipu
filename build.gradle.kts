plugins {
    java
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
}

group = "guru.ysy"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
}

extra["springAiVersion"] = "1.0.0-SNAPSHOT"
extra["openAPIVersion"] = "2.5.0"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.ai:spring-ai-zhipuai-spring-boot-starter:${property("springAiVersion")}")

    // OpenAPI
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${property("openAPIVersion")}")

    //lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    // lombok for test
    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // explicit declare JUnit5 for testing ready for gradle 9
    testImplementation("org.junit.jupiter:junit-jupiter")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.ai:spring-ai-bom:${property("springAiVersion")}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")

        showExceptions = true
        setExceptionFormat("full")
        showCauses = true
        showStackTraces = true

        showStandardStreams = true // show more verbose test output
    }
    // avoid dynamic agent loading warning for JDK21
    jvmArgs("-XX:+EnableDynamicAgentLoading")
}
