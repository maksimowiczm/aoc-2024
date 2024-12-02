package com.maksimowiczm.aoc2024

import kotlin.math.abs

object Day02 {
    val part1 = Solution { input ->
        input
            .lines()
            .map(String::trim)
            .filter(String::isNotEmpty)
            .map {
                val line = it.split("\\s+".toRegex()).map(String::toLong).toList()

                require(line.size >= 2) { "Each line must contain at least two numbers" }

                // Shortcut
                if (line[0] == line[1]) {
                    return@map false
                }

                val ascending = line[0] <= line[1]

                line.foldIndexed(true) { i, acc, next ->
                    if (!acc) return@foldIndexed false

                    val previous = line.getOrElse(i - 1) { return@foldIndexed acc }

                    if (ascending) {
                        next > previous && abs(next - previous) in 1..3
                    } else {
                        next < previous && abs(next - previous) in 1..3
                    }
                }
            }
            .count { it }
            .toLong()
    }
}
