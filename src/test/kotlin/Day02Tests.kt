import com.maksimowiczm.aoc2024.Day02
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day02Tests : FunSpec({
    test("Part 1 Example") {
        val input = """7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9
"""

        val result = Day02.part1(input)

        result shouldBe 2
    }

    test("Part 2 Example") {
        val input = """7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9
"""

        val result = Day02.part2(input)

        result shouldBe 4
    }

    test("Part 2 Example 2") {
        val input = """
        1 3 2 1
        """.trimIndent()

        val result = Day02.part2(input)

        result shouldBe 1
    }
})