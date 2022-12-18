private data class CrateMove(val amount: Int, val from: Int, val to: Int)

fun main() {
    val input = readInput("Day05")
    val stackDescription = input.takeWhile { it.isNotEmpty() }.dropLast(1).map {
        it.chunked(4).map { it[1] }
    }
    val crateMovement = input.dropWhile { it.isNotEmpty() }.drop(1).map {
        val description = it.split(' ')
        CrateMove(description[1].toInt(), description[3].toInt() - 1, description[5].toInt() - 1)
    }

    simulateCrateMovement(stackDescription, crateMovement) { stack, movement ->
        repeat(movement.amount) {
            stack[movement.to].add(stack[movement.from].removeLast())
        }
    }.println()
    simulateCrateMovement(stackDescription, crateMovement) { stack, movement ->
        stack[movement.to].addAll(stack[movement.from].takeLast(movement.amount))
        repeat(movement.amount) { stack[movement.from].removeLast() }
    }.println()
}

private fun simulateCrateMovement(
    stackDescription: List<List<Char>>, crateMovement: List<CrateMove>,
    moveInterpretation: (Array<ArrayDeque<Char>>, CrateMove) -> Unit
): String {
    val stack = Array(stackDescription[0].size) { ArrayDeque<Char>() }
    stackDescription.reversed().forEach {
        it.forEachIndexed { pileNumber, crate -> if (crate != ' ') stack[pileNumber].add(crate) }
    }
    crateMovement.forEach { moveInterpretation(stack, it) }
    return stack.joinToString("") { it.last().toString() }
}
