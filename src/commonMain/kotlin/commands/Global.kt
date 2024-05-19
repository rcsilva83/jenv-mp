package commands

import com.github.ajalt.clikt.core.CliktCommand

class Global: CliktCommand(help="Set or show the global Java version") {
    override fun run() {
        echo("Set or show the global Java version.")
    }
}