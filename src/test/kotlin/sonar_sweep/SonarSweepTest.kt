package sonar_sweep

import org.junit.jupiter.api.Test
import java.io.File


class SonarSweepTest {

    private val realTestData =
        File("src/test/resources/input_01_sonar_sweep.txt").useLines { it.toList() }.map { it.toInt() }


    @Test
    fun sonarSweepTest() {
        print(sonarSweep(realTestData))
    }

    @Test
    fun sonarSweepExtendedTest() {
        val testData = listOf(
            199,
            200,
            208,
            210,
            200,
            207,
            240,
            269,
            260,
            263
        )
        print(sonarSweepPartTwo(realTestData))
    }
}
