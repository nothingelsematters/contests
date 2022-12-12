@OptIn(ExperimentalStdlibApi::class)
fun viewSequence(trees: List<List<Int>>) = trees.innerIndexedSequence().map { (i, j, value) ->
    value to sequenceOf(
        trees[i][0..<j].asReversed(),
        trees[i][j + 1..<trees[i].size],
        trees[0..<i].map { it[j] }.asReversed(),
        trees[i + 1..<trees.size].map { it[j] },
    )
}

fun main() {
    val trees = mapLines { line -> line.map { it.toString().toInt() } }

    val first = viewSequence(trees).count { (tree, view) -> view.any { viewTrees -> viewTrees.all { tree > it } } }

    val second = viewSequence(trees).maxOf { (tree, view) ->
        view.map { viewTrees -> viewTrees.indexOfFirstOrNull { it >= tree }?.plus(1) ?: viewTrees.size }
            .reduce(Int::times)
    }

    println(first)
    println(second)
}
