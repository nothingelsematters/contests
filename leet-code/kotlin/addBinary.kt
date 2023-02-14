private fun Char?.toDigitOrZero() = this?.toString()?.toIntOrNull() ?: 0

fun addBinary(a: String, b: String): String {
    val reversed = buildString {
        var propagate = 0

        for (i in 0 until maxOf(a.length, b.length)) {
            val value = propagate +
                a.getOrNull(a.lastIndex - i).toDigitOrZero() +
                b.getOrNull(b.lastIndex - i).toDigitOrZero()
            append(if (value == 0 || value == 2) "0" else "1")
            propagate = if (value > 1) 1 else 0
        }

        if (propagate == 1) append("1")
    }

    return reversed.reversed()
}
