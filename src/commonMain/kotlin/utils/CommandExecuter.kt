package utils

import kotlinx.cinterop.*
import platform.posix.*
import kotlin.experimental.ExperimentalNativeApi

object CommandExecuter {

    @OptIn(ExperimentalForeignApi::class, ExperimentalNativeApi::class)
    fun executeCommand(command: String): String {

        val commandToExecute = "$command 2>&1"
        val fp = popen(commandToExecute, "r") ?: error("Failed to run command: $command")

        val stdout = buildString {
            val buffer = ByteArray(4096)
            while (true) {
                val input = fgets(buffer.refTo(0), buffer.size, fp) ?: break
                append(input.toKString())
            }
        }

        val status = pclose(fp)
        if (status != 0) {
            error("Command `$command` failed with status $status: $stdout")
        }

        return stdout.trim()
    }

}

@OptIn(ExperimentalForeignApi::class)
expect fun popen(commandToExecute: String, mode: String): CPointer<FILE /* = _iobuf */>?

@OptIn(ExperimentalForeignApi::class)
expect fun pclose(_File: CValuesRef<FILE /* = _iobuf */>?): Int