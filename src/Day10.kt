fun main() {
    fun part1(operations: List<Int>): Int {
        var x = 1
        return operations.mapIndexed { index, i ->
            val cycle = index + 1
            val signal = if (cycle <= 220 && (cycle - 20) % 40 == 0) x * cycle else 0
            x += i
            signal
        }.sum()
    }
    fun part2(operations: List<Int>): List<String> {
        var x = 1
        return operations.chunked(40).map {
            it.mapIndexed { index, i ->
                val char = if (index - 1 <= x && x <= index + 1) '#' else '.'
                x += i
                char
            }.joinToString("") { it.toString() }
        }
    }
    val input = readInput("Day10").flatMap {
        if (it == "noop") listOf(0) else listOf(0, it.drop("addx ".length).toInt())
    }
    part1(input).println()
    part2(input).forEach { it.println() }
}
