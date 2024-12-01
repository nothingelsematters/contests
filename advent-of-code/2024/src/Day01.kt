import kotlin.math.abs

fun main() {
    val (firstList, secondList) = mapLines { it.toInts().toPair() }.unzip()

    val first = firstList.asSequence()
        .sorted()
        .zip(secondList.asSequence().sorted())
        .sumOf { abs(it.second - it.first) }

    val appearances = secondList.groupingBy { it }.eachCount()
    val second = firstList.sumOf { it.toLong() * appearances.getOrDefault(it, 0) }

    println("$first $second")
}
