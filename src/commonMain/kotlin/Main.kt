import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands

import commands.*
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.toKString
import okio.Path
import okio.Path.Companion.toPath
import platform.posix.getenv
import utils.isWindows
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)
val userHomeDir: Path =
    (if (Platform.isWindows()) getenv("USERPROFILE") else getenv("HOME"))!!
        .toKString().toPath()

val JENV_HOME = userHomeDir.resolve(".jenv")

class Jenv: CliktCommand(name="jenv") {
    override fun run() = Unit
}

fun main(args: Array<String>) = Jenv()
    .subcommands(Init(), Add(), Versions(), Global(), Local(), Shell())
    .main(args)