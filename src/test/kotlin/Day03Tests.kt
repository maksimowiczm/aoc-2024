import com.maksimowiczm.aoc2024.Day03
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day03Tests : FunSpec({
    test("Part 1 Example") {
        val input = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
        val result = Day03.part1(input)
        result shouldBe 161
    }

    test("Part 2 Example") {
        val input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
        val result = Day03.part2(input)
        result shouldBe 48
    }
})