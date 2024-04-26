object Utils {
    private const val DEFAULT_PROVIDER = "other"
    private val PROVIDERS = linkedMapOf(
        "HotSpot" to "oracle",
        "Zulu" to "zulu",
        "Zing" to "zulu_prime",
        "GraalVM" to "graalvm",
        "Corretto" to "corretto",
        "SAP" to "sap",
        "Temurin" to "temurin",
        "JBR" to "jetbrains",
        "Kona" to "kona",
        "OpenLogic" to "openlogic",
        "Semeru\\ Runtime\\ Open" to "semeru",
        "Semeru\\ Runtime\\ Certified" to "semeru_certified",
        "Dragonwell" to "dragonwell",
        "J9" to "ibm",
        "OpenJDK" to "openjdk")

    fun generateAliases(javaVersion: String): List<String> {
        val provider = detectProvider(javaVersion)
        val platform = detectPlatform(javaVersion)
        val version = detectVersion(javaVersion)
        val shortVersion = detectShortVersion(version)
        return listOf(shortVersion, version, "$provider$platform-$version")
    }

    private fun detectProvider(javaVersion: String): String {
        for (provider in PROVIDERS) {
            if (javaVersion.contains(provider.key, true)) {
                return provider.value
            }
        }
        return DEFAULT_PROVIDER
    }

    private fun detectPlatform(javaVersion: String): String =
        if (javaVersion.contains("64-Bit")) {
            "64"
        } else {
            "32"
        }

    private fun detectVersion(javaVersion: String) =
        javaVersion.lines()
            .first { it.contains("version") }
            .replaceBefore('"', "")
            .replace("\"", "")
            .replace('_', '.')

    private fun detectShortVersion(version: String): String =
        "([0-9]\\.[0-9]).*".toRegex().matchEntire(version)?.groupValues?.get(1) ?: ""
}