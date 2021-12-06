package util

import java.io.File

fun getTestData(filename: String) = File("src/main/resources/$filename").useLines { it.toList() }


fun <T> List<List<T>>.column(index: Int): MutableList<T> = MutableList(size) { this[it][index] }


