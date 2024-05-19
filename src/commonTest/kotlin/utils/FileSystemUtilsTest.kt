package utils

import okio.FileSystem
import okio.Path.Companion.toPath
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertTrue

class FileSystemUtilsTest {

    @Test
    fun `Should create a link to Java directory`() {
        val targetDir = "temp".toPath()
        val fileName = "file_${Random.nextInt()}"
        val linkPath = "default".toPath()

        try {
            FileSystem.SYSTEM.createDirectory(targetDir)
            FileSystem.SYSTEM.write(targetDir.resolve(fileName), true) {
                write("Test file".encodeToByteArray())
            }

            FileSystemUtils.createLink(targetDir, linkPath)

            assertTrue("Link not found") { FileSystem.SYSTEM.exists(linkPath) }
            val filePath = linkPath.resolve(fileName)
            assertTrue("file $filePath not found") { FileSystem.SYSTEM.exists(filePath) }
        } finally {
            FileSystem.SYSTEM.delete(linkPath)
            FileSystem.SYSTEM.deleteRecursively(targetDir)
        }
    }


}