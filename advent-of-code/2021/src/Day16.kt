private operator fun Int.get(bit: Int) = shr(bit) and 1 == 1

private fun Boolean.toLong() = if (this) 1L else 0L

private fun List<Boolean>.toLong() = fold(0L) { acc, i -> (acc shl 1) or i.toLong() }

private fun List<Boolean>.toInt() = fold(0) { acc, i -> (acc shl 1) or i.toLong().toInt() }

private fun <T> MutableList<T>.steal(n: Int) = take(n).also { repeat(n) { removeFirst() } }

private sealed class Packet(open val typeId: Int) {

    abstract val version: Int

    abstract fun sumVersions(): Int

    abstract fun calculate(): Long

    companion object {
        fun fromBits(bits: MutableList<Boolean>): Packet {
            val version = bits.steal(3).toInt()
            val typeId = bits.steal(3).toInt()

            return if (typeId == 4) {
                Number.fromBits(version, bits)
            } else {
                Operator.fromBits(version, typeId, bits)
            }
        }
    }
}

private data class Number(override val version: Int, val number: Long): Packet(4) {

    override fun sumVersions() = version

    override fun calculate() = number

    companion object {
        fun fromBits(version: Int, bits: MutableList<Boolean>): Number {
            val numberBits = mutableListOf<Boolean>()

            while (bits.steal(1).single()) {
                numberBits.addAll(bits.steal(4))
            }
            numberBits.addAll(bits.steal(4))

            return Number(version, numberBits.toLong())
        }
    }
}

private data class Operator(
    override val version: Int,
    override val typeId: Int,
    val subPackets: List<Packet>
) : Packet(typeId) {

    override fun sumVersions() = version + subPackets.sumOf { it.sumVersions() }

    override fun calculate() = subPackets
        .asSequence()
        .map { it.calculate() }
        .run {
            when (typeId) {
                0 -> sum()
                1 -> reduce(Long::times)
                2 -> minOrNull().expect()
                3 -> maxOrNull().expect()
                5 -> toList().let { (a, b) -> (a > b).toLong() }
                6 -> toList().let { (a, b) -> (a < b).toLong() }
                7 -> (toSet().size == 1).toLong()
                else -> error("Illegal Operator")
            }
        }

    companion object {
        fun fromBits(version: Int, typeId: Int, bits: MutableList<Boolean>): Operator {
            val lengthTypeId = bits.removeFirst()

            val subPackets = if (lengthTypeId) {
                val packetLength = bits.steal(11).toLong()
                List(packetLength.toInt()) { fromBits(bits) }
            } else {
                val subPackets = mutableListOf<Packet>()
                val length = bits.steal(15).toInt()
                val subBits = bits.steal(length).toMutableList()

                while (subBits.isNotEmpty()) {
                    subPackets += fromBits(subBits)
                }
                subPackets
            }

            return Operator(version, typeId, subPackets)
        }
    }
}

fun main() {
    val bits = readln()
        .asSequence()
        .map { it.toString().toInt(16) }
        .flatMap { n -> (0..3).map { (n shr (3 - it)) and 1 == 1 } }
        .toList()

    val packet = Packet.fromBits(bits.toMutableList())
    println(packet.sumVersions())
    println(packet.calculate())
}
