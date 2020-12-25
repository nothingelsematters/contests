const val REMAINDER = 20201227L
const val SUBJECT = 7L

fun main() {
    val (doorPublicKey, cardPublicKey) = mapLines { it.toLong() }

    var loopSize = 0
    var i = 1L
    while (doorPublicKey != i) {
        i = (i * SUBJECT) % REMAINDER
        loopSize++
    }

    i = 1L
    repeat(loopSize) { i = (i * cardPublicKey) % REMAINDER }
    val first = i

    println(first)
}
