sealed class NumberFormat

data class Single(val number: Int) : NumberFormat() {
    override fun toString() = number.toString()
}

data class Range(val from: Int, var to: Int) : NumberFormat() {
    override fun toString() = if (to - from == 1) "$from,$to" else "$from-$to"
}

fun rangeExtraction(array: IntArray): String {
    val list = mutableListOf<NumberFormat>()

    for (value in array) {
        val last = list.lastOrNull()

        when {
            last is Single && last.number == value - 1 -> {
                list.removeLast()
                list.add(Range(value - 1, value))
            }
            last is Range && last.to == value - 1 -> last.to = value
            else -> list.add(Single(value))
        }
    }

    return list.joinToString(",")
}
