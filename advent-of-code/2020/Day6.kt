fun main() {
    val answers = System.`in`.bufferedReader().readText().splitToSequence("\n\n").map { it.split(' ', '\n') }.toList()

    val first = answers.sumBy { it.asSequence().flatMap { it.asSequence() }.distinct().count() }
    val second = answers.sumBy { groupAnswers ->
        groupAnswers.asSequence().filter { it.isNotEmpty() }.map { it.toSet() }.reduce(Set<Char>::intersect).size
    }

    println("$first $second")
}
