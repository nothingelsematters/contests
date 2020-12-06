fun main() {
    val seats = mapLines { line ->
        line.reversed().asSequence().mapIndexed { index, it -> (if (it in "RB") 1 else 0) shl index }.sum()
    }

    val first = seats.maxOrNull()
    val second = seats.asSequence()
        .sorted()
        .zipWithNext()
        .find { (first, second) -> first != second - 1 }
        ?.let { it.first + 1 }

    println("$first $second")
}
