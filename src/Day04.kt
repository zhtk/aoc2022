data class Assignment(val start: Int, val end: Int)

fun main() {
    val input = readInput("Day04").map {
        it.split(',').map {
            val range = it.split('-').map { it.toInt() }
            Assignment(range[0], range[1])
        }
    }
    input.count { assignmentIncludedIn(it[0], it[1]) || assignmentIncludedIn(it[1], it[0]) }.println()
}

fun assignmentIncludedIn(assignment: Assignment, includedIn: Assignment): Boolean =
    includedIn.start <= assignment.start && assignment.end <= includedIn.end
