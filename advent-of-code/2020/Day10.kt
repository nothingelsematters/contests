import java.lang.Integer.max

const val MAX_JUMP = 3

fun main() {
    var chain = mapLines { it.toInt() }.sorted().let { listOf(0) + it + listOf(it.last() + 3) }

    val first = chain.asSequence()
        .zipWithNext()
        .map { (a, b) -> b - a }
        .groupBy { it }
        .let { it[1]!!.size * it[3]!!.size }

    val ways = MutableList(chain.size) { 0L }
    ways[0] = 1L
    chain.asSequence().withIndex().drop(1).forEach { (index, el) ->
        ways[index] = (max(index - MAX_JUMP, 0)..index - 1).asSequence()
            .filter { el - chain[it] <= MAX_JUMP }
            .map { ways[it] }
            .sum()
    }
    val second = ways.last()

    println("$first $second")
}
