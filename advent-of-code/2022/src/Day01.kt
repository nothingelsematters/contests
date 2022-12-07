fun main() {
    val backpacks = mapBlocks { block -> block.asSequence().map { it.toInt() }.sum() }.sortedDescending()

    val first = backpacks[0]
    val second = backpacks[0..2].sum()
    println("$first $second")
}
