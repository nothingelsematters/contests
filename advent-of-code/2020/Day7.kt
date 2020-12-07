private const val MY_BAG = "shiny gold"

data class Edge(val from: String, val to: String, val value: Int)

fun dfs(
    bags: Map<String, List<Edge>>,
    direction: Edge.() -> String,
    visited: MutableMap<String, Int> = mutableMapOf(),
    current: String = MY_BAG
): Map<String, Int> {

    if (current !in visited) {
        val subbags = bags[current] ?: emptyList()
        visited[current] = 1 + subbags.sumBy {
            val key = it.direction()
            dfs(bags, direction, visited, key)
            it.value * dfs(bags, direction, visited, key)[key]!!
        }
    }

    return visited
}

fun main() {
    val bags = System.`in`.bufferedReader().useLines { lines ->
        lines.flatMap {
            val (source, targets) = it.split(" contain ")
            val sourceBag = source.dropLast(" bags".length)
            targets.splitToSequence(", ").mapNotNull {
                val (quantity, shade, colour) = it.split(' ')
                Edge(sourceBag, "$shade $colour", quantity.toIntOrNull() ?: return@mapNotNull null)
            }
        }
        .toList()
    }

    val straight = bags.groupBy(Edge::from) to Edge::to
    val reversed = bags.groupBy(Edge::to) to Edge::from

    val first = dfs(reversed.first, reversed.second).size - 1
    val second = dfs(straight.first, straight.second)[MY_BAG]!! - 1

    println("$first $second")
}
