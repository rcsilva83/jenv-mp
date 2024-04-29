import kotlin.test.Test
import kotlin.test.assertEquals

class UtilsTest {

    @Test
    fun `Should generate 3 aliases for Java 8 Temurin 64-bits`() {
        val javaVersion = """
            openjdk version "1.8.0_412"
            OpenJDK Runtime Environment (Temurin)(build 1.8.0_412-b08)
            OpenJDK 64-Bit Server VM (Temurin)(build 25.412-b08, mixed mode)
        """.trimIndent()

        val expected = setOf("1.8", "1.8.0.412", "temurin64-1.8.0.412")

        assertEquals(expected, Utils.generateAliases(javaVersion))
    }

    @Test
    fun `Should generate 4 aliases for Java 11 Corretto 64-bits`() {
        val javaVersion = """
            openjdk version "11.0.23" 2024-04-16 LTS
            OpenJDK Runtime Environment Corretto-11.0.23.9.1 (build 11.0.23+9-LTS)
            OpenJDK 64-Bit Server VM Corretto-11.0.23.9.1 (build 11.0.23+9-LTS, mixed mode)
        """.trimIndent()

        val expected = setOf("11", "11.0", "11.0.23", "corretto64-11.0.23")

        assertEquals(expected, Utils.generateAliases(javaVersion))
    }

    @Test
    fun `Should generate 4 aliases for Java 17 Temurin 32-bits`() {
        val javaVersion = """
            openjdk version "17.0.11" 2024-04-16
            OpenJDK Runtime Environment Temurin-17.0.11+9 (build 17.0.11+9)
            OpenJDK Client VM Temurin-17.0.11+9 (build 17.0.11+9, mixed mode, emulated-client)
        """.trimIndent()

        val expected = setOf("17", "17.0", "17.0.11", "temurin32-17.0.11")

        assertEquals(expected, Utils.generateAliases(javaVersion))
    }

}