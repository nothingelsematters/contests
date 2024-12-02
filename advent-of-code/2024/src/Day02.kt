private fun List<Int>.isSafe(): Boolean =
    asSequence().windowed(2).all { (a, b) -> a - b in 1..3 }
        || asSequence().windowed(2).all { (a, b) -> b - a in 1..3 }

fun main() {
    val reports = mapLines { it.toInts() }

    val first = reports.count { it.isSafe() }

    val second = reports.count { report ->
        report.isSafe() || report.indices.any { report.toMutableList().apply { removeAt(it) }.isSafe() }
    }

    println("$first $second")
}
