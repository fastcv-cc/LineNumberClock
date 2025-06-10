plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
    id("signing")
    id("tech.yanand.maven-central-publish") version ("1.3.0")
}

android {
    namespace = "cc.fastcv.line_number_clock"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            afterEvaluate { artifact(tasks.getByName("bundleReleaseAar")) }
            groupId = "cc.fastcv"
            artifactId = "line-number-clock"
            version = "1.0.1"

            pom {
                name = "LineNumberClock"
                description = "line number clock"
                url = "https://github.com/fastcv-cc/LineNumberClock"
                licenses {
                    license {
                        name = "The Apache License, Version 2.0"
                        url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
                    }
                }
                developers {
                    developer {
                        name = "pisto"
                        email = "sayhigerry@gmail.com"
                    }
                }
                scm {
                    url = "https://github.com/fastcv-cc/LineNumberClock"
                    connection = "scm:git:git://github.com/fastcv-cc/LineNumberClock.git"
                    developerConnection =
                        "scm:git:ssh://git@github.com:fastcv-cc/LineNumberClock.git"
                }
            }

        }
    }

    repositories {
    }
}


signing {
    sign(publishing.publications)
}

mavenCentral {
    authToken = rootProject.properties["authTokenValue"] as String
    // 上传是否应该自动发布。如果您希望手动执行此操作，请使用 'USER_MANAGED'。
    // 该属性是可选的，默认为 'AUTOMATIC'。
    publishingType = "AUTOMATIC"
    // 当发布类型为 'AUTOMATIC' 时，状态API获取 'PUBLISHING' 或 'PUBLISHED' 状态的最大等待时间
    // 或者当发布类型为 'USER_MANAGED' 时，获取 'VALIDATED' 状态。
    // 该属性是可选的，默认为60秒。
    maxWait = 60
}