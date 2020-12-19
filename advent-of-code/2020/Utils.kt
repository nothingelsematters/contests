private fun inputBufferedReader() = System.`in`.bufferedReader()

fun <T> mapLines(mapper: (String) -> T): MutableList<T> =
    inputBufferedReader().useLines { lines -> lines.map(mapper).toMutableList() }

fun readBlocks() = readText().split("\n\n")

fun readText(): String = inputBufferedReader().readText()

fun <T> T?.expect(): T = this ?: throw IllegalArgumentException("assertion failed")
