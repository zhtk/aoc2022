import kotlin.math.max

fun main() {
    var input = readInput("Day01")
    val sums = ArrayList<Int>()
    while (input.isNotEmpty()) {
        val calories = input.takeWhile { it.isNotEmpty() }
        sums.add(calories.sumOf { it.toInt() })
        input = input.drop(calories.size + 1)
    }
    sums.sortDescending()
    sums[0].println()
    sums.take(3).sum().println()
}
