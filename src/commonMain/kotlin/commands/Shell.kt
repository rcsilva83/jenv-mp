package commands

import com.github.ajalt.clikt.core.CliktCommand

class Shell: CliktCommand(help="Set or show the shell-specific Java version") {
    override fun run() {
        echo("Set or show the shell-specific Java version.")
    }
}