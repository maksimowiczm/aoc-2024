package com.maksimowiczm.aoc2024

object Day03 {
    val part1 = Solution { input ->
        val regex = "mul\\((?<left>\\d+),(?<right>\\d+)\\)".toRegex()
        val matches = regex.findAll(input.trim())

        matches.map { match ->
            val left = match.groups["left"]?.value?.toLong() ?: 0
            val right = match.groups["right"]?.value?.toLong() ?: 0
            left * right
        }.sum()
    }

    // THIS IS PAIN, NO PATTERN MATCHING IN KOTLIN
    val part2 = Solution {
        val tokens = Lexer()(it).toList()
        val expressions = Parser()(tokens).toList()

        var doMul = true
        var sum = 0L

        for (expression in expressions) {
            when (expression) {
                is Expression.Do -> doMul = true
                is Expression.DoNot -> doMul = false
                is Expression.Mul -> if (doMul) sum += expression.left * expression.right
            }
        }

        sum
    }
}

private sealed interface Expression {
    data object Do : Expression
    data object DoNot : Expression
    data class Mul(val left: Long, val right: Long) : Expression
}

private class Parser {
    operator fun invoke(tokens: List<Token>): Sequence<Expression> = sequence {
        var index = 0

        while (index < tokens.size) {
            when {
                tokens[index] is Token.Do -> {
                    yield(Expression.Do)
                    index++
                }

                tokens[index] is Token.DoNot -> {
                    yield(Expression.DoNot)
                    index++
                }

                index + 5 < tokens.size &&
                        tokens[index] is Token.Mul &&
                        tokens[index + 1] is Token.OpenParenthesis &&
                        tokens[index + 2] is Token.Number &&
                        tokens[index + 3] is Token.Comma &&
                        tokens[index + 4] is Token.Number &&
                        tokens[index + 5] is Token.CloseParenthesis -> {
                    yield(
                        Expression.Mul(
                            (tokens[index + 2] as Token.Number).value,
                            (tokens[index + 4] as Token.Number).value
                        )
                    )
                    index += 6
                }

                else -> index++
            }
        }
    }
}

private sealed interface Token {
    data object Mul : Token
    data object OpenParenthesis : Token
    data object CloseParenthesis : Token
    data object Do : Token
    data object DoNot : Token
    data class Number(val value: Long) : Token
    data object Comma : Token
    data object Other : Token
}

private class Lexer {
    operator fun invoke(input: String): Sequence<Token> = sequence {
        var index = 0
        while (index < input.length) {
            when (val char = input[index]) {
                'm' -> {
                    if (index + 3 >= input.length) {
                        index++
                        continue
                    }

                    if (input[index + 1] == 'u' && input[index + 2] == 'l') {
                        yield(Token.Mul)
                        index += 3
                    } else {
                        index++
                    }
                }

                'd' -> {
                    if (index + 4 >= input.length) {
                        index++
                        continue
                    }

                    val substring = input.substring(index, index + 4)

                    if (substring == "do()") {
                        yield(Token.Do)
                        index += 4
                        continue
                    }

                    if (index + 6 >= input.length) {
                        index++
                        continue
                    }

                    val substr = input.substring(index, index + 5)

                    if (substr == "don't") {
                        yield(Token.DoNot)
                        index += 5
                        continue
                    }

                    index++
                }

                '(' -> {
                    yield(Token.OpenParenthesis)
                    index++
                }

                ')' -> {
                    yield(Token.CloseParenthesis)
                    index++
                }

                ',' -> {
                    yield(Token.Comma)
                    index++
                }

                in '0'..'9' -> {
                    val number = input.substring(index).takeWhile { it.isDigit() }
                    yield(Token.Number(number.toLong()))
                    index += number.length
                }

                else -> {
                    index++
                    yield(Token.Other)
                    continue
                }
            }
        }
    }
}