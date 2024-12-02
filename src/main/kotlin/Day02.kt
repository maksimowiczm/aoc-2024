package com.maksimowiczm.aoc2024

import kotlin.math.abs

object Day02 {
    val part1 = Solution { input ->
        input
            .lines()
            .map(String::trim)
            .filter(String::isNotEmpty)
            .map { it.split("\\s+".toRegex()).map(String::toLong) }
            .map(::isReportSafe)
            .count { it }
            .toLong()
    }

    val part2 = Solution { input ->
        val reports = input
            .lines()
            .map(String::trim)
            .filter(String::isNotEmpty)
            .map { it.split("\\s+".toRegex()).map(String::toLong) }

        // BRUTE FORCE because input size is small
        val results = reports.map { report ->
            report
                .withIndex()
                .asSequence()
                .map { (i, _) -> report.filterIndexed { j, _ -> i != j } }
                .map(::isReportSafe)
                .find { it }
        }

        results.filterNotNull().count { it }.toLong()
    }

    private fun isReportSafe(report: List<Long>): Boolean {
        if (report.size < 2) {
            return true
        }

        val ascending = report[0] <= report[1]

        return report.foldIndexed(true) { i, acc, next ->
            if (!acc) return@foldIndexed false

            val previous = report.getOrElse(i - 1) { return@foldIndexed acc }

            if (ascending) {
                next > previous && abs(next - previous) in 1..3
            } else {
                next < previous && abs(next - previous) in 1..3
            }
        }
    }
}
