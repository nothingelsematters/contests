import kotlin.math.pow

private fun Long.pow(rhs: Int) = toDouble().pow(rhs).toLong()

private fun String.snafuToDecimal(): Long =
    reversed()
        .asSequence()
        .mapIndexed { index, c ->
            5L.pow(index) * when (c) {
                in '0'..'2' -> c.toString().toInt()
                '-' -> -1
                '=' -> -2
                else -> expect()
            }
        }
        .sum()

private fun Long.toSnafu(): String {
    fun Long.toDigit() = when (this) {
        in 0L..2L -> this.toString()
        -1L -> "-"
        -2L -> "="
        else -> throw IllegalArgumentException()
    }

    fun Long.internalToSnafu(power: Int): String? = when {
        power == 0 && this in -2..2 -> this.toDigit()
        power == 0 -> null
        this > 5L.pow(power + 1) - 1 -> null
        else -> (-2L..2L).asSequence()
            .mapNotNull { i ->
                (this - i * 5L.pow(power)).internalToSnafu(power - 1)?.let { i.toDigit() + it }
            }
            .firstOrNull()
    }

    val power = (0..100).find { this@toSnafu / 5L.pow(it) <= 2 }!!
    return internalToSnafu(power + 1)!!
}

fun main() {
    println(mapLines { it.snafuToDecimal() }.sum().toSnafu())
}
