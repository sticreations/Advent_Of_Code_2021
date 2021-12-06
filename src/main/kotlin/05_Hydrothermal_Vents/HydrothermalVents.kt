package HydrothermalVents

import util.getTestData
import kotlin.math.absoluteValue

class HydroField(max: Int) {
    val gameField = MutableList(max + 1) {
        MutableList(max + 1) { 0 }
    }

    fun String.replaceWithPlusOne(): String {
        var edited = this
        if (this == ".") {
            edited = "1"
        } else if (this.toIntOrNull() != null) {
            edited = (this.toInt() + 1).toString()
        }
        return edited
    }

    fun hydro(vents: List<List<Int>>, withDiagonal : Boolean = false): Int {
        vents
            .forEach { vent ->
                if (vent[0] == vent[2]) {
                    // horizontal
                    gameField.horizontalInc(vent)
                } else if (vent[1] == vent[3]) {
                    gameField.verticalInc(vent)
                } else {
                    println("Line is not Horizontal or Vertical")
                    if(withDiagonal) gameField.diagonalInc(vent)
                }
            }
        return gameField.map { it.filter { it > 1 }.size }.sum()
    }
}

fun MutableList<MutableList<Int>>.horizontalInc(vector: List<Int>) = run {
    val fromTo = arrayOf(vector[1], vector[3])
    this[vector[0]].subList(fromTo.minOrNull()!!, fromTo.maxOrNull()!! + 1).replaceAll { it + 1 }
}

fun MutableList<MutableList<Int>>.verticalInc(vector: List<Int>) = run {
    val fromTo = arrayOf(vector[0], vector[2])
    for (i in fromTo.minOrNull()!!..fromTo.maxOrNull()!!) {
        this[i][vector[1]] += 1
    }
}

fun MutableList<MutableList<Int>>.diagonalInc(vector: List<Int>) = run {
    if ((vector[0] - vector[2]) == (vector[1] - vector[3])) {
        val fromToX = arrayOf(vector[0], vector[2])
        val fromToY = arrayOf(vector[1], vector[3])
        for (i in 0..(fromToX.maxOrNull()!! - fromToX.minOrNull()!!)) {
            this[fromToX.minOrNull()!! + i][fromToY.minOrNull()!! + i] += 1
        }
    } else if ((vector[0] - vector[2]).absoluteValue == (vector[1] - vector[3]).absoluteValue) {
        if ((vector[0] - vector[2])>0) {
            for (i in 0..(vector[0] - vector[2])) {
                this[vector[0] - i][vector[1] + i] += 1
            }
        } else {
            for (i in 0..(vector[0] - vector[2]).absoluteValue) {
                this[vector[0] + i][vector[1] - i] += 1
            }
        }
    } else {
        println("wrong input")
    }
}

fun main() {
    println("Day Five - Hydrothermal Vents")
    val testData = getTestData("input_05_hydrothermal_vents.txt").map {
        it.split(" -> ").flatMap { it.split(",").map { it.toInt() } }
    }
    val field = HydroField(testData.maxOf { it.maxOrNull()!! })
    println("Solution to Part 1 is ${field.hydro(testData)}")
    println("Solution to Part 2 is ${field.hydro(testData, true)}")
}
