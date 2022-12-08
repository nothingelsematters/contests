fun main() {
    val backpacks = mapBlocks { block -> block.sumOf { it.toInt() } }.sortedDescending()

    val first = backpacks[0]
    val second = backpacks[0..2].sum()
    println("$first $second")
}
