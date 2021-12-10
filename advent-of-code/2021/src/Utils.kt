private fun inputBufferedReader() = System.`in`.bufferedReader()

fun <T> mapLines(mapper: (String) -> T): MutableList<T> =
    inputBufferedReader().useLines { lines -> lines.map(mapper).toMutableList() }

fun <T> mapBlocks(mapper: (String) -> T): MutableList<List<T>> =
    inputBufferedReader()
        .readText()
        .trim('\n')
        .splitToSequence("\n\n")
        .map { it.lineSequence().map(mapper).toList() }
        .toMutableList()

fun String.toInts(delimiter: String = " "): List<Int> =
    splitToSequence(delimiter).filter { it.isNotEmpty() }.map { it.toInt() }.toList()

fun <T> T?.expect(): T = this ?: error("Invalid input")

fun expect(): Nothing = error("Invalid Input")
