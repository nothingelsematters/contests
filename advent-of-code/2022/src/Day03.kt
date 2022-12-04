fun Sequence<List<String>>.sumBackpacksCost(): Int =
    sumOf { backpacks ->
        backpacks
            .asSequence()
            .map { it.toSet() }
            .reduce { a, b -> a intersect b }
            .sumOf {
                when (it) {
                    in 'a'..'z' -> it - 'a' + 1
                    in 'A'..'Z' -> it - 'A' + 27
                    else -> expect()
                }
            }
    }

fun main() {
    val backpacks = mapLines { it }
    val first = backpacks.asSequence().map { it.chunked(it.length / 2) }.sumBackpacksCost()
    val second = backpacks.asSequence().chunked(3).sumBackpacksCost()

    println("$first $second")
}
