import com.maksimowiczm.aoc2024.Day01
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe


class Day01Tests : FunSpec({
    test("Part 1 Example") {
        val input = """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3
        """.trimIndent()

        val result = Day01.part1(input)

        result shouldBe 11
    }

    test("Part 2 Example") {
        val input = """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3
        """.trimIndent()

        val result = Day01.part2(input)

        result shouldBe 31
    }
})