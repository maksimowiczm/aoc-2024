package com.maksimowiczm.aoc2024

import kotlin.math.abs

object Day01 {
    private fun parseInput(input: String): Pair<List<Long>, List<Long>> = input
        .lines()
        .asSequence()
        .map(String::trim)
        .filter(String::isNotEmpty)
        .map { line ->
            line.split("\\s+".toRegex()).map { it.toLong() }
        }
        .map {
            require(it.size == 2) { "Each line must contain exactly two numbers" }
            it[0] to it[1]
        }
        .unzip()
        .apply {
            require(first.size == second.size) { "Both lists must have the same size" }
        }

    val part1 = Solution { input ->
        val (left, right) = parseInput(input)

        val result = left.sorted().zip(right.sorted()).fold(0L) { acc, (l, r) ->
            acc + abs(r - l)
        }

        result
    }

    val part2 = Solution {
        val (left, right) = parseInput(it)

        val counts = right.fold(mutableMapOf<Long, Long>()) { acc, r ->
            acc[r] = acc.getOrDefault(r, 0) + 1
            acc
        }

        val result = left.fold(0L, { acc, l ->
            acc + l * (counts[l] ?: 0)
        })

        result
    }
}
