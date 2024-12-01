package com.maksimowiczm.aoc2024

import kotlin.math.abs

object Day01 {
    val part1 = Solution { input ->
        val (left, right) = input
            .lines()
            .asSequence()
            .map(String::trim)
            .filter(String::isNotEmpty)
            .map { line ->
                line.split("\\s+".toRegex()).map { it.toInt() }
            }
            .map {
                require(it.size == 2) { "Each line must contain exactly two numbers" }
                it[0] to it[1]
            }
            .unzip()
            .apply {
                require(first.size == second.size) { "Both lists must have the same size" }
            }

        // Just use kotlin
        val result = left.sorted().zip(right.sorted()).fold(0) { acc, (l, r) ->
            acc + abs(r - l)
        }

        result.toLong()
    }
}
