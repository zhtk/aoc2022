data class Assignment(val start: Int, val end: Int)

fun main() {
    val input = readInput("Day04").map {
        it.split(',').map {
            val range = it.split('-').map { it.toInt() }
            Assignment(range[0], range[1])
        }
    }
    input.count { assignmentsContained(it[0], it[1]) }.println()
    input.count { isOverlap(it[0], it[1]) }.println()
}

fun assignmentIncludedIn(assignment: Assignment, includedIn: Assignment): Boolean =
    includedIn.start <= assignment.start && assignment.end <= includedIn.end

fun assignmentsContained(assignment1: Assignment, assignment2: Assignment): Boolean =
    assignmentIncludedIn(assignment1, assignment2) ||
            assignmentIncludedIn(assignment2, assignment1)

fun isOverlap(assignment1: Assignment, assignment2: Assignment): Boolean =
    assignment1.start <= assignment2.start && assignment2.start <= assignment1.end ||
            assignment1.start <= assignment2.end && assignment2.end <= assignment1.end ||
            assignmentsContained(assignment1, assignment2)
