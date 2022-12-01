fun main() {
    val backpacks = withBlocks { block -> block.asSequence().map { it.toInt() }.sum() }.sortedDescending()

    val first = backpacks[0]
    val second = backpacks[0..2].sum()
    println("$first $second")
}
