private fun isSatisfiable(target: Long, left: MutableList<Long>, rules: (Long, Long) -> List<Long>): Boolean {
    if (target < left.last()) return false
    if (left.size == 1) return target == left.single()

    val last = left.removeLast()
    val penultimate = left.removeLast()

    rules(last, penultimate).forEach { element ->
        left += element
        if (isSatisfiable(target, left, rules)) return true
        left.removeLast()
    }

    left += penultimate
    left += last
    return false
}

private fun calibrationResult(equations: List<List<Long>>, rules: (Long, Long) -> List<Long>): Long =
    equations.asSequence()
        .filter { equation ->
            isSatisfiable(equation.first(), equation.subList(1, equation.size).asReversed().toMutableList(), rules)
        }
        .sumOf { it.first() }


fun main() {
    val equations = mapLines { it.toLongs(": ", " ") }

    val first = calibrationResult(equations) { a, b -> listOf(a + b, a * b) }
    val second = calibrationResult(equations) { a, b -> listOf(a + b, a * b, "$a$b".toLong()) }

    println("$first $second")
}
