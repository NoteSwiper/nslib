plugins {
    id("java")
    id("maven-publish")
    id("eclipse")
    id("idea")
    signing
}

val jomlVersion = "1.10.5"

val gVMaj: String by project
val gVMin: String by project
val gVPat: String by project
val gVPre: String by project
val gVBui: String by project

val projectMainClass: String by project

val projectVersion = "${gVMaj}.${gVMin}.${gVPat}-${gVPre}+${gVBui}"
val projectGroupId: String by project
val projectName: String by project
val projectAuthor: String by project
val projectDescription: String by project

group = projectGroupId
version = projectVersion


repositories {
    mavenCentral()
}

dependencies {
    /* [[ JUnit + JUnit Jupiter ]] */
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    /* [[ Apache Log4j ]] */
    implementation(platform("org.apache.logging.log4j:log4j-bom:2.22.0"))

    implementation("org.apache.logging.log4j:log4j-api")
    runtimeOnly("org.apache.logging.log4j:log4j-core")

    /* [[ Google Guava ]] */
    implementation("com.google.guava:guava:32.1.3-jre")
    implementation("com.google.guava:failureaccess:1.0.2")

    /* [[ Google GSON ]] */
    implementation("com.google.code.gson:gson:2.10.1")

    /* [[ Electronwill Night Config - YAML + TOML + JSON + HOCON ]] */
    implementation("com.electronwill.night-config:core:3.6.7")
    implementation("com.electronwill.night-config:yaml:3.6.7")
    implementation("com.electronwill.night-config:toml:3.6.7")
    implementation("com.electronwill.night-config:json:3.6.7")
    implementation("com.electronwill.night-config:hocon:3.6.7")

    /* [[ Oshi JSON ]] */
    implementation("com.github.oshi:oshi-core:6.4.8")
    implementation("com.github.oshi:oshi-json:3.13.6")

    /* [[ IBM Icu4j ]] */
    implementation("com.ibm.icu:icu4j:74.1")

    /* [[ Commons-codec + io + logging ]] */
    implementation("commons-codec:commons-codec:1.16.0")
    implementation("commons-io:commons-io:2.15.1")
    implementation("commons-logging:commons-logging:1.3.0")

    /* [[ Classgraph ]] */
    implementation("io.github.classgraph:classgraph:4.8.165")

    /* [[ Netty ]] */
    implementation("io.netty:netty-all:4.1.101.Final")

    /* [[ Unimi DSI Fastutil ]] */
    implementation("it.unimi.dsi:fastutil:8.5.12")

    implementation("org.joml:joml:${jomlVersion}")

    runtimeOnly("io.github.spair:imgui-java-natives-windows:1.86.11")
    implementation("io.github.spair:imgui-java-binding:1.86.11")

    implementation("org.tinylog:tinylog-api:2.6.2")
    implementation("org.tinylog:tinylog-impl:2.6.2")
    implementation("org.tinylog:jcl-tinylog:2.6.2")
    implementation("org.tinylog:jsl-tinylog:2.6.2")
    implementation("org.tinylog:jul-tinylog:2.6.2")
}

java {
    withJavadocJar()
    withSourcesJar()
}

tasks {
    wrapper {
        distributionType = Wrapper.DistributionType.ALL
    }
    test {
        useJUnitPlatform()
    }
    processResources {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
    jar {
        manifest {
            attributes(
                    "Specification-Title" to projectName,
                    "Specification-Vendor" to projectAuthor,
                    "Specification-Version" to projectVersion,
                    "Implementation-Title" to projectName,
                    "Implementation-Vendor-Id" to "git.noteswiper",
                    "Implementation-Vendor" to projectAuthor,
                    "Implementation-Version" to projectVersion
            )
        }
    }
    withType<JavaCompile> {
        sourceCompatibility = JavaVersion.VERSION_17.toString()
        targetCompatibility = JavaVersion.VERSION_17.toString()
        options.encoding = "UTF-8"
    }
    javadoc {
        if (JavaVersion.current().isJava9Compatible) {
            (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
        }
    }
}

sourceSets {
    main {
        java {
            srcDir("src/main/java")
        }
        resources {
            srcDir("src/main/resources")
        }
    }
    test {
        java {
            srcDir("src/test/java")
        }
        resources {
            srcDir("src/test/resources")
        }
    }
}

idea {
    module {
        inheritOutputDirs = true
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "nslib"
            from(components["java"])
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
            pom {
                name.set(projectName)
                description.set(projectDescription)
                inceptionYear.set("2023")
                packaging = "jar"
                url.set("https://github.com/NoteSwiper/nslib")
                licenses {
                    license {
                        name.set("CC BY-NC-SA 4.0")
                        url.set("http://creativecommons.org/licenses/by-nc-sa/4.0/")
                    }
                }
                developers {
                    developer {
                        id.set("noteswiper")
                        email.set("ryuuking0410b@gmail.com")
                        name.set("NoteSwiper")
                        timezone.set("Asia/Tokyo")
                        url.set("https://github.com/NoteSwiper")
                    }
                }
                scm {
                    url.set("https://github.com/NoteSwiper/nslib")
                }
                issueManagement {
                    system.set("GitHub Issue Tracker")
                    url.set("https://github.com/NoteSwiper/nslib/issues")
                }
            }
        }
    }
    repositories {
    }
}

signing {
    sign(publishing.publications["mavenJava"])
}