fun play(start: List<Int>, iteration: Int) {
    val indices = values.dropLast(1).asSequence().withIndex().associate { (index, it) -> it to index }.toMutableMap()

    var last = values.last()

    for (i in values.size until iteration) {
        val index = indices[last]
        indices[last] = i - 1
        last = if (index == null) 0 else i - 1 - index
    }

    return last
}

fun main() {
    val values = readLine()!!.splitToSequence(",").map { it.toInt() }.toList()
    val first = play(values, 2020)
    val second = play(values, 30000000)
    // WARN probably set -Xmx3g to extend heap size
    println("$first $second")
}
