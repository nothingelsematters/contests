fun treeAmount(trees: List<List<Boolean>>, right: Int, down: Int): Int {
    var i = 0
    var j = 0
    var count = 0

    while (i < trees.size) {
        if (trees[i][j]) {
            count++
        }
        i += down
        j = (j + right) % trees.first().size
    }
    return count
}

fun main() {
    val trees = System.`in`.bufferedReader().useLines { lines ->
        lines.map { line -> line.asSequence().map { it == '#' }.toList() }.toList()
    }

    val first = treeAmount(trees, 3, 1)
    val second = sequenceOf(1 to 1, 3 to 1, 5 to 1, 7 to 1, 1 to 2)
        .map { (right, down) -> treeAmount(trees, right, down).toLong() }
        .reduce(Long::times)
    println("$first $second")
}
