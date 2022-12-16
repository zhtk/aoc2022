enum class Shape { PAPER, ROCK, SCISSORS }
enum class RoundOutcome { WIN, DRAW, LOSS }

val shapeWinsWithMap = mapOf(Shape.PAPER to Shape.ROCK, Shape.ROCK to Shape.SCISSORS, Shape.SCISSORS to Shape.PAPER)

data class Round(val you: Shape, val opponent: Shape) {
    fun getOutcome(): RoundOutcome = when {
        you == opponent -> RoundOutcome.DRAW
        shapeWinsWithMap[you] == opponent -> RoundOutcome.WIN
        else -> RoundOutcome.LOSS
    }
}

fun main() {
    val opponentMoves = mapOf("A" to Shape.ROCK, "B" to Shape.PAPER, "C" to Shape.SCISSORS)
    val yourMoves = mapOf("X" to Shape.ROCK, "Y" to Shape.PAPER, "Z" to Shape.SCISSORS)
    val yourOutcomes = mapOf("X" to RoundOutcome.LOSS, "Y" to RoundOutcome.DRAW, "Z" to RoundOutcome.WIN)
    val outcomePoints = mapOf(RoundOutcome.LOSS to 0, RoundOutcome.DRAW to 3, RoundOutcome.WIN to 6)
    val shapePoints = mapOf(Shape.ROCK to 1, Shape.PAPER to 2, Shape.SCISSORS to 3)

    val input = readInput("Day02").filterNot { it.isEmpty() }.map { it.split(' ') }
    val strategy1 = input.map { Round(yourMoves[it[1]]!!, opponentMoves[it[0]]!!) }
    strategy1.sumOf { outcomePoints[it.getOutcome()]!! + shapePoints[it.you]!! }.println()
    val strategy2 = input.map {
        val opponentMove = opponentMoves[it[0]]!!
        Round(getCorrectMove(yourOutcomes[it[1]]!!, opponentMove), opponentMove)
    }
    strategy2.sumOf { outcomePoints[it.getOutcome()]!! + shapePoints[it.you]!! }.println()
}

fun getCorrectMove(expectedOutcome: RoundOutcome, opponentMove: Shape): Shape = when (expectedOutcome) {
    RoundOutcome.DRAW -> opponentMove
    RoundOutcome.WIN -> shapeWinsWithMap.entries.firstOrNull { it.value == opponentMove }!!.key
    RoundOutcome.LOSS -> shapeWinsWithMap[opponentMove]!!
}
