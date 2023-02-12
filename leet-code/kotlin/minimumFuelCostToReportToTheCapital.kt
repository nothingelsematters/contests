fun minimumFuelCost(roads: Array<IntArray>, seats: Int): Long {
    val graph = roads.asSequence()
        .flatMap { (a, b) -> sequenceOf(a to b, b to a) }
        .groupBy({ it.first }) { it.second }

    val cityNumber = roads.size + 1
    val parents = MutableList(cityNumber) { 0 }
    val depths = mutableListOf<List<Int>>()

    var q = listOf(0)
    val visited = MutableList(cityNumber) { false }
    visited[0] = true
    var i = 1

    while (q.isNotEmpty()) {
        q = q.flatMap { parent ->
            graph[parent]
                .orEmpty()
                .asSequence()
                .filter { !visited[it] }
                .onEach {
                    parents[it] = parent
                    visited[it] = true
                }
        }
        depths += q
        i++
    }

    val men = MutableList(cityNumber) { 1L }
    var litres = 0L

    depths.asReversed()
        .asSequence()
        .withIndex()
        .forEach { (index, depthLevel) ->
            depthLevel.forEach {
                men[parents[it]] += men[it]
                litres += men[it] / seats + if (men[it] % seats != 0L) 1 else 0
            }
        }

    return litres
}
