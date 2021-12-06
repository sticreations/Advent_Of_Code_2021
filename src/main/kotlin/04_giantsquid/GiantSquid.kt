package giantsquid

import util.column
import util.getTestData

fun List<Int>.checkForBingo(): Boolean {
    val equalRows = windowed(5, 5, false).map { row -> row.all { it == 0 } }
    if (equalRows.any { it }) {
        return true
    }
    for (i in 0 until 5) {
        val equalCol = windowed(5, 5).column(i).all { it == 0 }
        if (equalCol) {
            return true
        }
    }
    return false
}

fun squidOne(input: List<Int>, boards: MutableList<MutableList<Int>>, rowSize: Int = 5) =
    input.forEach { drawnNumber ->
        boards.mapIndexed { index, board ->
            board.replaceAll { if (it == drawnNumber) 0; else it };
            if (board.checkForBingo()) {
                println("Board ${index + 1} won. The Winning Number is ${board.sum() * drawnNumber}")
                return
            }
        }
    }

fun squidTwo(
    input: List<Int>,
    boards: MutableList<MutableList<Int>>,
    winners: MutableList<Int> = mutableListOf<Int>()
) = input.forEach { drawnNumber ->
    boards.mapIndexed { index, board ->
        board.replaceAll { if (it == drawnNumber) 0; else it };
        if (board.checkForBingo()) {
            if (!winners.contains(index) && winners.distinct().size == boards.size - 1) {
                println("Board ${index + 1} is the last one. Winning Number is ${board.sum() * drawnNumber}")
                return
            }
            winners.add(index)
        }
    }
}

fun createInput(testData: List<String>) = testData.first().split(",").map { it.toInt() }
fun createBoards(testData: List<String>) =
    testData.drop(2).windowed(5, 6)
        .map { board ->
            board.map { row ->
                row.windowed(2, 3).map { it.replace(" ", "").toInt() }
            }.flatten().toMutableList()
        }
        .toMutableList()

fun main() {
    println("Day Four - Giant Squid")
    val testData = getTestData("input_04_giant_squid.txt")
    val input = createInput(testData)
    val boards = createBoards(testData)
    squidOne(input, boards)
    squidTwo(input, boards)
}
