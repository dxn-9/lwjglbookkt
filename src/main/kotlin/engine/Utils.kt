package org.lwjglbookkt.engine

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

class Utils {
    companion object {
        fun readFile(filePath: String): String {
            val str: String
            try {
                str = String(Files.readAllBytes(Paths.get(filePath)))
            } catch (exception: IOException) {
                throw RuntimeException("Erorr reading file [$filePath] - $exception")
            }
            return str
        }
    }
}