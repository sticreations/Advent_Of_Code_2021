package dive

import sonar_sweep.sonarSweep
import util.getTestData


fun divePartOne(command: List<Pair<String, Int>>): Int {
    var x = 0
    var y = 0
    command.forEach {
        when (it.first) {
            "forward" -> {
                x += it.second
            }
            "down" -> {
                y += it.second
            }
            "up" -> {
                y -= it.second
            }
        }
    }
    return x * y
}

fun divePartTwo(command: List<Pair<String, Int>>): Int {
    var x = 0
    var y = 0
    var aim = 0
    command.forEach {
        when (it.first) {
            "forward" -> {
                x += it.second
                y += aim * it.second
            }
            "down" -> {
                aim += it.second
            }
            "up" -> {
                aim -= it.second
            }
        }
    }
    return x * y
}

fun main() {
    val testData = getTestData("input_02_dive.txt").map { Pair(it.substringBefore(" "), it.substringAfter(" ").toInt()) }
    println("Day Two - Dive")
    println("Part One: ${divePartOne(testData)}")
    println("Part Two: ${divePartTwo(testData)}")
}
