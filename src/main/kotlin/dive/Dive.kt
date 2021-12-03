package dive

import util.getTestData

fun divePartOne(command: List<Pair<String, Int>>): Int =
    command.map {
        when (it.first) {
            "forward" -> Pair(it.second, 0)
            "down" -> Pair(0, it.second)
            "up" -> Pair(0, -it.second)
            else -> Pair(0, 0)
        }
    }.reduce { acc, pair -> Pair(acc.first + pair.first, acc.second + pair.second) }
        .let { it.first * it.second }

data class Dive(
    var x: Int = 0,
    var y: Int = 0,
    var aim: Int = 0,
)

fun Dive.forward(i: Int) {
    x += i; y += aim * i; }

fun Dive.down(i: Int) {
    aim += i
}

fun Dive.up(i: Int) {
    aim -= i
}

fun divePartTwo(command: List<Pair<String, Int>>): Int {
    with(Dive()) {
        command.forEach {
            when (it.first) {
                "forward" -> this.forward(it.second)
                "down" -> this.down(it.second)
                "up" -> this.up(it.second)
            }
        }
        return this.x*this.y
    }
}


fun main() {
    val testData =
        getTestData("input_02_dive.txt").map { Pair(it.substringBefore(" "), it.substringAfter(" ").toInt()) }
    println("Day Two - Dive")
    println("Part One: ${divePartOne(testData)}")
    println("Part Two: ${divePartTwo(testData)}")
}
