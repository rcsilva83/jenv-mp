package utils

import okio.FileSystem
import okio.Path
import platform.posix.perror
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class)
fun Platform.isWindows() = osFamily == OsFamily.WINDOWS

object FileSystemUtils {

    @OptIn(ExperimentalNativeApi::class)
    fun createLink(targetDirPath: Path, linkPath: Path): Boolean {
        return if (FileSystem.SYSTEM.exists(linkPath)) {
            println("${linkPath.name} already present, skip installation")
            false
        } else {
            ensureParentDirExists(linkPath)
            if (Platform.isWindows()) {
                val linkPathWindows = linkPath.toString().replace('/', '\\')
                val targetDirPathWindows = targetDirPath.toString().replace('/', '\\')
                val command = "mklink /J $linkPathWindows $targetDirPathWindows"
                try {
                    CommandExecuter.executeCommand(command)
                } catch (e: Error) {
                    perror("Error creating junction: $e")
                }
            } else {
                FileSystem.SYSTEM.createSymlink(linkPath, targetDirPath)
            }
            println("${linkPath.name} added")
            true
        }
    }

    private fun ensureParentDirExists(targetDirPath: Path) {
        targetDirPath.parent?.let {
            if (!FileSystem.SYSTEM.exists(it)) {
                FileSystem.SYSTEM.createDirectories(it)
            }
        }
    }
}