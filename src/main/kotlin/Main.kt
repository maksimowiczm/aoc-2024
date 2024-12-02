package com.maksimowiczm.aoc2024

import java.io.File
import kotlin.system.exitProcess


fun main(args: Array<String>) {
    if (args.size < 3) {
        println("Usage: <day> <part> <input-file>")
        exitProcess(0)
    }

    val solution: Solution? = when (args[0]) {
        "1" -> when (args[1]) {
            "1" -> Day01.part1
            "2" -> Day01.part2
            else -> null
        }

        "2" -> when (args[1]) {
            "1" -> Day02.part1
            else -> null
        }

        else -> null
    }

    if (solution == null) {
        println("Solution not found")
        exitProcess(1)
    }

    val input = try {
        File(args[2]).readText()
    } catch (e: Exception) {
        System.err.println("Error reading file: ${e.message}")
        exitProcess(1)
    }

    val result = solution(input)
    println(result)
}
