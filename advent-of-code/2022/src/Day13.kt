private sealed class ListOrInt : Comparable<ListOrInt> {

    override fun compareTo(other: ListOrInt): Int = when (this) {
        is WrappedInt -> when (other) {
            is WrappedInt -> value compareTo other.value
            is WrappedList -> WrappedList(this) compareTo other
        }
        is WrappedList -> when (other) {
            is WrappedInt -> -(other compareTo this)
            is WrappedList -> list.asSequence()
                .zip(other.list.asSequence())
                .map { (a, b) -> a compareTo b }
                .find { it != 0 }
                ?: (list.size compareTo other.list.size)
        }
    }

    companion object {
        fun fromString(string: String) = parseFromString(string).first

        private fun parseFromString(string: String): Pair<ListOrInt, String> {
            return if (string[0].isDigit()) {
                val numberString = string.takeWhile { it.isDigit() }
                WrappedInt(numberString.toInt()) to string.substring(numberString.length)
            } else {
                var currentString = string.substring(1)

                val list = buildList {
                    while (currentString.isNotEmpty() && currentString[0] != ']') {
                        val (element, newString) = parseFromString(currentString)
                        add(element)
                        currentString = newString.trimStart(',')
                    }
                }

                WrappedList(list) to currentString.drop(1)
            }
        }
    }
}

private data class WrappedList(val list: List<ListOrInt>): ListOrInt() {
    constructor(vararg listOrInt: ListOrInt) : this(listOrInt.toList())
}

private data class WrappedInt(val value: Int) : ListOrInt()

private fun part1(pairs: List<Pair<ListOrInt, ListOrInt>>) = pairs.asSequence()
    .withIndex()
    .filter { (_, i) -> i.first compareTo i.second <= 0 }
    .sumOf { it.index + 1 }

private fun part2(pairs: List<Pair<ListOrInt, ListOrInt>>): Int {
    val additional = listOf(WrappedList(WrappedList(WrappedInt(2))), WrappedList(WrappedList(WrappedInt(6))))
    val sorted = pairs.asSequence().flatMap { it.toList() }.let { it + additional }.sorted().toList()
    return additional.asSequence().map { sorted.indexOf(it) + 1 }.reduce(Int::times)
}

fun main() {
    val pairs = mapBlocks { it.asSequence().map(ListOrInt::fromString).toList().toPair() }
    println(part1(pairs))
    println(part2(pairs))
}
