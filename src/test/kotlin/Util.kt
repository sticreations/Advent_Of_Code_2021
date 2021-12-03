import java.io.File

private val testData =
    File("src/test/resources/input_02_dive.txt")
        .useLines { it.toList() }
        .map { Pair(it.substringBefore(" "), it.substringAfter(" ").toInt()) }

fun getTestData(path: String) = File(path).useLines { it.toList() }
