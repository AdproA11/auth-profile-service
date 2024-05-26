plugins {
	java
	jacoco
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
	// sonarqube
	id("org.sonarqube") version "3.5.0.2730"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

sonarqube {
    properties {
        property("sonar.projectKey", "AdproA11_auth-profile-service")
        property("sonar.sources", "src/main")
        property("sonar.tests", "src/test")
        property("sonar.java.coveragePlugin", "jacoco")
        property("sonar.jacoco.reportPaths", "build/jacoco/test.exec")
    }
}

java {
        sourceCompatibility = JavaVersion.VERSION_21
        toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("javax.xml.bind:jaxb-api:2.2.4")
	implementation("org.postgresql:postgresql")
	implementation("io.jsonwebtoken:jjwt:0.2")
	implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")
	implementation("org.springframework.cloud:spring-cloud-starter-gateway-mvc:4.1.3")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("io.micrometer:micrometer-registry-prometheus")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-rest")
	implementation("javax.persistence:javax.persistence-api:2.2")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("com.h2database:h2")
	implementation("com.h2database:h2")
	implementation("org.jacoco:org.jacoco.core:0.8.7")
}


tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.test {
   useJUnitPlatform()
   finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
	reports {
		xml.required = true
		html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml")) // specify your directory here
	}
	dependsOn(tasks.test)
}