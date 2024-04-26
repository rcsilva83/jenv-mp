package commands

import com.github.ajalt.clikt.core.CliktCommand

class Init: CliktCommand(help="Configure the shell environment for jenv") {
    override fun run() {
        echo("Configure the shell environment for jenv.")
    }
}