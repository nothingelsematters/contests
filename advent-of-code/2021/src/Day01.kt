fun List<Int>.countWindowIncrease(size: Int) =
    asSequence().windowed(size) { it.sum() }.zipWithNext().count { (a, b) -> a < b }

fun main() {
    val lines = mapLines { it.toInt() }
    val first = lines.countWindowIncrease(1)
    val second = lines.countWindowIncrease(3)
    println("$first $second")
}
