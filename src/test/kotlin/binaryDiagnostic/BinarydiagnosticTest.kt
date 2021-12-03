package binaryDiagnostic

import getTestData
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


internal class BinarydiagnosticTest{
    val testData = getTestData("src/test/resources/input_03_binary_dia.txt")

    val exampledata = listOf(
        "00100",
        "11110",
        "10110",
        "10111",
        "10101",
        "01111",
        "00111",
        "11100",
        "10000",
        "11001",
        "00010",
        "01010"
    )

    @Test
    fun binaryOne() {
        print(binaryDiagnostic(testData))
    }

    @Test
    fun binaryTwo() {
        assertEquals(5852595, binaryDiagnosticPartTwo(testData))
    }

}
