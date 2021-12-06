package Lanternfish

import HydrothermalVents.HydroField
import HydrothermalVents.test
import util.getTestData

class Lanternfish {
}


/**
 *
After one day, its internal timer would become 2.
After another day, its internal timer would become 1.
After another day, its internal timer would become 0.
After another day, its internal timer would reset to 6, and it would create a new lanternfish with an internal timer of 8.
After another day, the first lanternfish would have an internal timer of 5, and the second lanternfish would have an internal timer of 7
 */

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
