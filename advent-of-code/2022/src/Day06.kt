private fun String.positionDifferent(n: Int) =
    asSequence().windowed(n) { it.toSet() }.indexOfFirst { it.size == n } + n

fun main() {
    val line = readln()
    println(line.positionDifferent(4))
    println(line.positionDifferent(14))
}
