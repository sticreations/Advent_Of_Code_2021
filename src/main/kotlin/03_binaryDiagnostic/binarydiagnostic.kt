package `03_binaryDiagnostic`

import util.getTestData

fun binaryDiagnostic(input: List<String>): Int {
    var gamma = ""
    for (i in input[0].indices) {
        gamma += input.map { it[i] }.groupBy { it }.maxByOrNull { it.value.size }?.key
    }
    val epsilon = gamma.map { if (it == '0') '1' else '0' }.joinToString("")
    return gamma.toInt(2) * epsilon.toInt(2)
}

fun List<String>.filterBy(pos: Int, defaultWhenEqual: String, sort: (Map<Char, List<Char>>) -> Char): List<String> {
    if (this.size == 1) return this
    val groupedInput = this.map { it[pos] }.groupBy { it }
    val isEqual = groupedInput.values.toMutableList()[0].size == groupedInput.values.toMutableList()[1].size
    return this.filter {
        if (isEqual) {
            it[pos].toString() == defaultWhenEqual
        } else it[pos] == sort(groupedInput)
    }
}

fun binaryDiagnosticPartTwo(input: List<String>): Int {
    var filteredOx = input
    var filteredCo2 = input
    for (i in input[0].indices) {
        filteredOx = filteredOx.filterBy(i, "1") { it.maxByOrNull { it.value.size }?.key!! }
        filteredCo2 = filteredCo2.filterBy(i, "0") { it.minByOrNull { it.value.size }?.key!! }
    }
    return filteredOx[0].toInt(2) * filteredCo2[0].toInt(2)
}

fun main() {
    val testData = getTestData("input_03_binary_dia.txt")
    println("Day Three - Binary Diagnostic")
    println("Part One: ${binaryDiagnostic(testData)}")
    println("Part Two: ${binaryDiagnosticPartTwo(testData)}")
}
