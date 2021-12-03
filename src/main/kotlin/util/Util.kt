package util

import java.io.File

fun getTestData(filename: String) = File("src/main/resources/$filename").useLines { it.toList() }
