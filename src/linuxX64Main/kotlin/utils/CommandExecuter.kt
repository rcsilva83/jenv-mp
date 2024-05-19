package utils

import kotlinx.cinterop.*
import platform.posix.*

@OptIn(ExperimentalForeignApi::class)
actual fun popen(commandToExecute: String, mode: String): CPointer<FILE /* = _iobuf */>? =
    platform.posix.popen(commandToExecute, mode)

@OptIn(ExperimentalForeignApi::class)
actual fun pclose(_File: CValuesRef<FILE /* = _iobuf */>?): Int =
    platform.posix.pclose(_File)