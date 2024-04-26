package commands

import com.github.ajalt.clikt.core.CliktCommand

class Versions: CliktCommand(help="List all Java versions available to jenv") {
    override fun run() {
        echo("List all Java versions available to jenv.")
    }
}