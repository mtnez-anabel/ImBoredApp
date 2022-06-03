package com.anabelmm.imboredapp.model

import java.io.InputStreamReader

class MockResponseFileReader {
    companion object Content {
        fun content(path: String): String {
            val reader = InputStreamReader(
                MockResponseFileReader::class.java.classLoader?.getResourceAsStream(path)
            )
            val content = reader.readText()
            println()
            reader.close()
            return content
        }
    }
}