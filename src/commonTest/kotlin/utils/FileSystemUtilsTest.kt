package utils

import okio.FileSystem
import okio.Path.Companion.toPath
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FileSystemUtilsTest {

    @Test
    fun `Should create a link to directory`() {
        val targetDir = "temp".toPath()
        val fileName = "file_${Random.nextInt()}"
        val linkPath = "default".toPath()

        try {
            FileSystem.SYSTEM.createDirectory(targetDir)
            FileSystem.SYSTEM.write(targetDir.resolve(fileName), true) {
                write("Test file".encodeToByteArray())
            }

            assertTrue { FileSystemUtils.createLink(targetDir, linkPath) }

            assertTrue("Link not found") { FileSystem.SYSTEM.exists(linkPath) }
            val filePath = linkPath.resolve(fileName)
            assertTrue("file $filePath not found") { FileSystem.SYSTEM.exists(filePath) }
        } finally {
            FileSystem.SYSTEM.delete(linkPath)
            FileSystem.SYSTEM.deleteRecursively(targetDir)
        }
    }

    @Test
    fun `Should create parent folder for link to directory`() {
        val targetDir = "temp".toPath()
        val fileName = "file_${Random.nextInt()}"
        val linkPath = "notpresent/default".toPath()

        try {
            FileSystem.SYSTEM.createDirectory(targetDir)
            FileSystem.SYSTEM.write(targetDir.resolve(fileName), true) {
                write("Test file".encodeToByteArray())
            }

            assertTrue { FileSystemUtils.createLink(targetDir, linkPath) }

            assertTrue("Link not found") { FileSystem.SYSTEM.exists(linkPath) }
            val filePath = linkPath.resolve(fileName)
            assertTrue("file $filePath not found") { FileSystem.SYSTEM.exists(filePath) }
        } finally {
            FileSystem.SYSTEM.deleteRecursively(linkPath.parent!!)
            FileSystem.SYSTEM.deleteRecursively(targetDir)
        }
    }

    @Test
    fun `Should not create a link that already exists`() {
        val targetDir = "temp".toPath()
        val fileName = "file_${Random.nextInt()}"
        val linkPath = "notpresent/default".toPath()

        try {
            FileSystem.SYSTEM.createDirectory(targetDir)
            FileSystem.SYSTEM.write(targetDir.resolve(fileName), true) {
                write("Test file".encodeToByteArray())
            }

            assertTrue { FileSystemUtils.createLink(targetDir, linkPath) }
            assertFalse { FileSystemUtils.createLink(targetDir, linkPath) }
        } finally {
            FileSystem.SYSTEM.deleteRecursively(linkPath.parent!!)
            FileSystem.SYSTEM.deleteRecursively(targetDir)
        }
    }

}