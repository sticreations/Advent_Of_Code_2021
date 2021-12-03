package binaryDiagnostic

import dive.divePartOne
import dive.divePartTwo
import util.getTestData

fun binaryDiagnostic(input: List<String>): Int {
    var gamma: String = ""
    var epsilon: String = ""
    for (i in input[0].indices) {
        val digit = input.map { it[i] }.groupBy { it }
        gamma += digit.maxByOrNull { it.value.size }?.key
        epsilon += digit.minByOrNull { it.value.size }?.key
    }
    val calcGamma = gamma.toInt(2)
    val calcEpsilon = epsilon.toInt(2)

    return calcGamma * calcEpsilon
}

fun List<String>.filterBy(pos: Int, defaultWhenEqual: String, maxmin: (Map<Char, List<Char>>) -> Char): List<String> {
    val groupedInput = this.map { it[pos] }.groupBy { it }
    val isEqual = groupedInput.values.toMutableList()[0].size == groupedInput.values.toMutableList()[1].size
    val max = groupedInput.maxByOrNull { it.value.size }?.key
    val min = groupedInput.minByOrNull { it.value.size }?.key
    return this.filter {
        if (isEqual) {
            it[pos].toString() == defaultWhenEqual
        } else it[pos] == maxmin(groupedInput)
    }
}

fun binaryDiagnosticPartTwo(input: List<String>): Int {
    var filteredOx = input
    var filteredCo2 = input
    for (i in input[0].indices) {
        if (filteredOx.size != 1) {
            filteredOx = filteredOx.filterBy(i,"1") { it.maxByOrNull { it.value.size }?.key!! }
        }
        if (filteredCo2.size != 1) {
            filteredCo2 = filteredCo2.filterBy(i,"0") { it.minByOrNull { it.value.size }?.key!! }
        }
    }
    val calcOx = filteredOx[0].toInt(2)
    val calcC02 = filteredCo2[0].toInt(2)
    return calcOx * calcC02
}
fun main() {
    val testData = getTestData("input_03_binary_dia.txt")
    println("Day Three - Binary Diagnostic")
    println("Part One: ${binaryDiagnostic(testData)}")
    println("Part Two: ${binaryDiagnosticPartTwo(testData)}")
}
