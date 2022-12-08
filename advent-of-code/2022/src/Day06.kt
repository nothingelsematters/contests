private fun String.positionDifferent(n: Int) = windowedSequence(n).indexOfFirst { it.toSet().size == n } + n

fun main() {
    val line = readln()
    println(line.positionDifferent(4))
    println(line.positionDifferent(14))
}
