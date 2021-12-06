package Lanternfish

import util.getTestData

fun MutableMap<Int, Long>.incAt(at: Int, amount: Long) = this.set(at, (this[at] ?: 0) + amount)

fun calculateFishes(fishes: List<Int>, days: Int): Long {
    val newFish = mutableMapOf<Int, Long>()
    val allFish: MutableMap<Int, Long> = fishes.groupBy { it }
        .mapValues { it.value.size.toLong() }
        .toMutableMap()
    for (i in 0 until days) {
        allFish.map { (age, count) ->
            when (age) {
                0 -> newFish.incAt(6, count)
                else -> newFish.incAt(age - 1, count)
            }
        }
        allFish[0]?.let { newFish[8] = it }
        allFish.clear()
        newFish.forEach { allFish.incAt(it.key, it.value) }
        newFish.clear()
    }
    return allFish.values.sum()
}

fun main() {
    println("Day Six - Lanternfish")
    val lanternfishes = getTestData("input_06_lanternfish.txt")[0].split(",").map { it.toInt() }.toMutableList()
    println("Solution to Part 1 is ${calculateFishes(lanternfishes, 80)}")
    println("Solution to Part 2 is ${calculateFishes(lanternfishes, 256)}")
}
