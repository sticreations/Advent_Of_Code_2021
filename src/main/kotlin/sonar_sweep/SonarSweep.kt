package sonar_sweep

import util.getTestData

fun sonarSweep(sweeps: List<Int>): Int =sweeps.zipWithNext{a,b -> b-a}.count { it>0 }

fun sonarSweepPartTwo(sweeps: List<Int>): Int = sonarSweep(sweeps.windowed(3,1) { (a,b,c) -> a+b+c })

fun main() {
    val testData = getTestData("input_01_sonar_sweep.txt").map { it.toInt() }
    println("Day One - Sonar Sweep")
    println("Part One: ${sonarSweep(testData)}")
    println("Part Two: ${sonar_sweep.sonarSweepPartTwo(testData)}")
}
