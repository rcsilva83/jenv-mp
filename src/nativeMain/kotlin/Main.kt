import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands

import commands.*

const val JENV_ROOT = "~/.jenv"

class Jenv: CliktCommand(name="jenv") {
    override fun run() = Unit
}

fun main(args: Array<String>) = Jenv()
    .subcommands(Init(), Add(), Versions(), Global(), Local(), Shell())
    .main(args)