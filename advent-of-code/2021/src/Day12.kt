private data class Vertex(val identifier: String, val transitions: MutableList<Vertex> = mutableListOf()) {

    fun isBig() = identifier.all { it.isUpperCase() }

    override fun hashCode() = identifier.hashCode()

    override fun equals(other: Any?) = identifier == (other as? Vertex)?.identifier

    override fun toString() = "$identifier: ${transitions.map { it.identifier }}"
}

private class Graph(edges: List<Pair<String, String>>) {

    val vertices = edges.asSequence().flatMap { it.toList() }.map { it to Vertex(it) }.toMap()

    val start = vertices["start"]!!

    val end = vertices["end"]!!

    init {
        for ((a, b) in edges) {
            vertices[a]!!.transitions += vertices[b]!!
            vertices[b]!!.transitions += vertices[a]!!
        }
    }

    fun findPathNumber(smallCaveVisitTwice: Int) = findPathNumber(end, smallCaveVisitTwice)

    private fun findPathNumber(
        vertex: Vertex,
        smallCaveVisitTwice: Int,
        visited: MutableSet<Vertex> = mutableSetOf(),
        smallCaveTwice: MutableSet<Vertex> = mutableSetOf(),
    ): Int =
        when {
            vertex == start -> 1
            vertex in visited &&
                (vertex == end || smallCaveTwice.size == smallCaveVisitTwice || vertex in smallCaveTwice) -> 0
            else -> {
                if (!vertex.isBig() ) {
                    if (vertex in visited) {
                        smallCaveTwice += vertex
                    } else {
                        visited += vertex
                    }
                }

                val result =  vertex.transitions.sumOf {
                    findPathNumber(it, smallCaveVisitTwice, visited, smallCaveTwice)
                }

                if (vertex in smallCaveTwice) {
                    smallCaveTwice -= vertex
                } else {
                    visited -= vertex
                }

                result
            }
        }
}

fun main() {
    val edges = mapLines { it.split('-').toPair() }
    val graph = Graph(edges)

    println(graph.findPathNumber(0))
    println(graph.findPathNumber(1))
}
