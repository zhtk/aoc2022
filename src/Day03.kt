fun main() {
    val input = readInput("Day03")
    input.sumOf {
        val firstCompartment = it.substring(0, it.length / 2).toSet()
        val secondCompartment = it.substring(it.length / 2, it.length).toSet()
        val itemInBothCompartments = firstCompartment.find { secondCompartment.contains(it) }!!
        getPriority(itemInBothCompartments)
    }.println()
    input.chunked(3).sumOf { chunk ->
        chunk[0].filter { chunk[1].contains(it) }.first { chunk[2].contains(it) }.let { getPriority(it) }
    }.println()
}

fun getPriority(item: Char): Int = if (item in 'A'..'Z') item - 'A' + 27 else item - 'a' + 1
