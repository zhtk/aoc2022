fun main() {
    data class Move(val x: Int, val y: Int, val times: Int)
    fun part1(operations: List<Move>): Int {
        
        var tailX = 0
        var tailY = 0
        return 0
    }
    fun part2(operations: List<Int>): Int {
        return 0
    }
    val input = readInput("Day09").map {
        val (operation, times) = it.split(' ')
        when (operation) {
            "U" -> Move(0, -1, times.toInt())
            "L" -> Move(-1, 0, times.toInt())
            "R" -> Move(1, 0, times.toInt())
            else -> Move(0, 1, times.toInt())
        }
    }
    part1(input).println()
}
