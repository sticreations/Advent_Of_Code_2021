package HydrothermalVents

import giantsquid.createBoards
import giantsquid.createInput
import giantsquid.squidOne
import giantsquid.squidTwo
import util.column
import util.getTestData
import kotlin.math.absoluteValue


/*
For now, only consider horizontal and vertical lines: lines where either x1 = x2 or y1 = y2.
In this diagram, the top left corner is 0,0 and the bottom right corner is 9,9.
 Each position is shown as the number of lines which cover that point or . if no line covers that point.
  The top-left pair of 1s, for example, comes from 2,2 -> 2,1;
  the very bottom row is formed by the overlapping lines 0,9 -> 5,9 and 0,9 -> 2,9.
  Consider only horizontal and vertical lines. At how many points do at least two lines overlap?
 */
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


fun test() {
    val field = HydroField(4)
    // 4,1 -> 2,3
    val l = listOf(4, 0, 0, 4)

    field.gameField.diagonalInc(l)
    field.gameField.forEach { println(it) }
}

fun main() {
    println("Day Five - Hydrothermal Vents")
    test()
    val testData = getTestData("input_05_hydrothermal_vents.txt").map {
        it.split(" -> ").flatMap { it.split(",").map { it.toInt() } }
    }
    val field = HydroField(testData.maxOf { it.maxOrNull()!! })
    println("Solution to Part 1 is ${field.hydro(testData)}")
    println("Solution to Part 2 is ${field.hydro(testData, true)}")

}
