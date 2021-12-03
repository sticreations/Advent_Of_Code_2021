package sonar_sweep

import util.getTestData

fun sonarSweep(sweeps: List<Int>): Int {
    val increased = sweeps.mapIndexed { index, i ->
        if (index == 0) {
            false
        } else i > sweeps.get(index - 1)
    }
    return increased.count { it }
}

fun sonarSweepPartTwo(sweeps: List<Int>): Int {
    val slidingWindows = sweeps.mapIndexed { index, i ->
        i.plus(sweeps.getOrElse(index + 1) { 0 }).plus(sweeps.getOrElse(index + 2) { 0 })
    }
    return sonarSweep(slidingWindows)
}

fun main() {
    val testData = getTestData("input_01_sonar_sweep.txt").map { it.toInt() }
    println("Day One - Sonar Sweep")
    println("Part One: ${sonarSweep(testData)}")
    println("Part Two: ${sonar_sweep.sonarSweepPartTwo(testData)}")
}
