package utils

import okio.FileSystem
import okio.Path
import platform.posix.perror
import platform.posix.system
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class)
fun Platform.isWindows() = osFamily == OsFamily.WINDOWS

object FileSystemUtils {

    @OptIn(ExperimentalNativeApi::class)
    fun createLink(targetDirPath: Path, linkPath: Path) {
        if (FileSystem.SYSTEM.exists(linkPath)) {
            println("${linkPath.name} already present, skip installation")
        } else {
            ensureParentDirExists(linkPath)
            if (Platform.isWindows()) {
                val linkPathWindows = linkPath.toString().replace('/', '\\')
                val targetDirPathWindows = targetDirPath.toString().replace('/', '\\')
                // TODO Use system call
                val command = "mklink /J $linkPathWindows $targetDirPathWindows"
                val result = system(command)
                if (result == -1) {
                    perror("Error creating junction")
                }
            } else {
                FileSystem.SYSTEM.createSymlink(linkPath, targetDirPath)
            }
        }
    }

    private fun ensureParentDirExists(targetDirPath: Path) {
        targetDirPath.parent?.let {
            if (!FileSystem.SYSTEM.exists(it)) {
                println("Creating dir $it")
                FileSystem.SYSTEM.createDirectories(it)
            }
        }
    }
}