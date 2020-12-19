fun main() {
    val answers = readBlocks().map { it.split(' ', '\n') }

    val first = answers.sumBy { it.asSequence().flatMap { it.asSequence() }.distinct().count() }
    val second = answers.sumBy { groupAnswers ->
        groupAnswers.asSequence().filter { it.isNotEmpty() }.map { it.toSet() }.reduce(Set<Char>::intersect).size
    }

    println("$first $second")
}
