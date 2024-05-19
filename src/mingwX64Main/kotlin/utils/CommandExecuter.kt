package utils

import kotlinx.cinterop.*
import platform.posix.*

@OptIn(ExperimentalForeignApi::class)
actual fun popen(commandToExecute: String, mode: String): CPointer<FILE /* = _iobuf */>? =
    platform.posix._popen(commandToExecute, mode)

@OptIn(ExperimentalForeignApi::class)
actual fun pclose(_File: CValuesRef<FILE /* = _iobuf */>?): Int =
    platform.posix._pclose(_File)