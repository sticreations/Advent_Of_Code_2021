package sonar_sweep

fun sonarSweep(sweeps: List<Int>): Int {
    val increased = sweeps.mapIndexed { index, i ->
        if (index == 0) {
            false
        } else i > sweeps.get(index - 1)
    }
    return increased.count { it }
}

fun sonarSweepPartTwo(sweeps: List<Int>): Int {
    val slidingWindows = sweeps.mapIndexed { index, i -> i.plus(sweeps.getOrElse(index+1) { 0 }).plus(sweeps.getOrElse(index+2) { 0 }) }
    return sonarSweep(slidingWindows)
}
