package dive

import org.junit.jupiter.api.Test
import java.io.File

internal class DiveTest {

    private val testData =
        File("src/test/resources/input_02_dive.txt")
            .useLines { it.toList() }
            .map { Pair(it.substringBefore(" "), it.substringAfter(" ").toInt()) }

    @Test
    fun partTwo() {
        print(divePartTwo(testData))
    }

    @Test
    fun partOne() {
        print(divePartOne(testData))
    }



}
