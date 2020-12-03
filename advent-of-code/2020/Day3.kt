fun treeAmount(trees: List<List<Boolean>>, right: Int, down: Int): Int {
    var j = 0
    return trees.slice(trees.indices step down)
        .asSequence()
        .filter { it[j].also { j = (j + right) % trees.first().size } }
        .count()
}

fun main() {
    val trees = mapLines { it.map { it == '#' } }

    val first = treeAmount(trees, 3, 1)
    val second = sequenceOf(1 to 1, 3 to 1, 5 to 1, 7 to 1, 1 to 2)
        .map { (right, down) -> treeAmount(trees, right, down).toLong() }
        .reduce(Long::times)
    println("$first $second")
}
