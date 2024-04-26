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
    fun `Should generate 4 aliases for Java 21 Corretto 32-bits`() {
        val javaVersion = """
            openjdk version "21.0.3" 2024-04-16 LTS
            OpenJDK Runtime Environment Corretto-21.0.3.9.1 (build 21.0.3+9-LTS)
            OpenJDK Server VM Corretto-21.0.3.9.1 (build 21.0.3+9-LTS, mixed mode, sharing)
        """.trimIndent()

        val expected = setOf("21", "21.0", "21.0.3", "corretto32-21.0.3")

        assertEquals(expected, Utils.generateAliases(javaVersion))
    }

}