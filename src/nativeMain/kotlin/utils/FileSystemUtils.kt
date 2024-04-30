package utils

import okio.FileSystem
import okio.Path
import platform.posix.perror
import platform.posix.system
import kotlin.experimental.ExperimentalNativeApi

object FileSystemUtils {

    @OptIn(ExperimentalNativeApi::class)
    fun createLink(targetDirPath: Path, linkPath: Path) {
        if (Platform.osFamily == OsFamily.WINDOWS) {
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