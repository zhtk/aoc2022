fun main() {
    val input = readInput("Day06")[0]
    (4..input.length).first { input.substring(it - 4, it).toCharArray().toSet().size == 4 }.println()
    (14..input.length).first { input.substring(it - 14, it).toCharArray().toSet().size == 14 }.println()
}
