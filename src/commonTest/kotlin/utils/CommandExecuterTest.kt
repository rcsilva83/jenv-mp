package utils

import kotlin.experimental.ExperimentalNativeApi
import kotlin.test.*

@OptIn(ExperimentalNativeApi::class)
class CommandExecuterTest {

    @Test
    fun `Should execute command successfully`() {

        val command = if (Platform.osFamily == OsFamily.WINDOWS) "dir" else  "ls"

        assertFalse(CommandExecuter.executeCommand(command).isBlank())
    }

    @Test
    fun `Should thrown an error on invalid command execution`() {

        val command = "invalid"

        assertFails { CommandExecuter.executeCommand(command) }
    }
}