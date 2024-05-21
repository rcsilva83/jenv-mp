package commands

import com.github.ajalt.clikt.core.CliktCommand

class Local: CliktCommand(help="Set or show the local application-specific Java version") {
    override fun run() {
        echo("Set or show the local application-specific Java version.")
    }
}