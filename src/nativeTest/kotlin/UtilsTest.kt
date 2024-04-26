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

        val expected = listOf("1.8", "1.8.0.412", "temurin64-1.8.0.412")

        assertEquals(expected, Utils.generateAliases(javaVersion))
    }

}