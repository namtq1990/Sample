import org.gradle.api.JavaVersion

object config {

    object android {

        object app {
            object id {
                const val base = "com.quangnam.sample"
                const val debug = "$base${suffix.debug}"
                const val staging = "$base${suffix.staging}"
                const val release = "$base${suffix.release}"
            }

            object suffix {
                const val debug = ".debug"
                const val staging = ".staging"
                const val release = ".hammer"
            }

            object sdk {
                const val min: Int = 23
                const val target: Int = 29
                const val compile: Int = 29
            }
        }
    }

    object compiler {
        val java = JavaVersion.VERSION_1_8
        const val jvm = "1.8"
    }
}