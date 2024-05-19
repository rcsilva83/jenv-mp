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
        if (Platform.isWindows()) {
            // TODO Use system call
            val command = "mklink /J $linkPath $targetDirPath"
            val result = system(command)
            if (result == -1) {
                perror("Error creating junction")
            }
        } else {
            FileSystem.SYSTEM.createSymlink(linkPath, targetDirPath)
        }
    }
}