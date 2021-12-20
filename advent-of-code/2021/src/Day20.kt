private fun decode(mask: List<Boolean>, encoded: List<List<Boolean>>, evenIteration: Boolean = true) =
    List(encoded.size + 2) { i ->
        List(encoded.first().size + 2) { j ->
            ((i - 2..i) cartesian (j - 2..j))
                .map { (currentI, currentJ) ->
                    encoded.getOrNull(currentI)?.getOrNull(currentJ) ?:
                        if (evenIteration) mask.last() else mask.first()
                }
                .toList()
                .toInt()
                .let { mask[it] }
        }
    }

private fun decode(mask: List<Boolean>, encoded: List<List<Boolean>>, iterations: Int) =
    (0 until iterations).fold(encoded) { acc, i -> decode(mask, acc, i and 1 != 1) }

private fun List<List<Boolean>>.countLit() = sumOf { it.count { it } }

fun main() {
    val (mask, encoded) = mapBlocks { block -> block.map { it == '#' } }
        .let { (mask, encoded) -> mask.single() to encoded }

    val first = decode(mask, encoded, 2).countLit()
    val second = decode(mask, encoded, 50).countLit()
    println("$first $second")
}
