fun main() {
    fun correctOrder(happensBefore: Map<Int, List<Int>>, update: List<Int>): Boolean {
        val visited = mutableSetOf<Int>()

        return update.all { page ->
            visited += page
            happensBefore[page].orEmpty().all { it in visited || it !in update }
        }
    }

    val (order, updates) = mapBlocks { block -> block.map { it.toInts(",", "|") } }

    val happensBefore = order.groupBy({ it[1] }, { it[0] })

    val first = updates.asSequence()
        .filter { correctOrder(happensBefore, it) }
        .sumOf { it[it.size / 2] }

    val second = updates.asSequence()
        .filterNot { correctOrder(happensBefore, it) }
        .sumOf { update ->
            val currentUpdate = update.toMutableSet()
            var lastValue = 0

            repeat(update.size / 2 + 1) {
                lastValue = currentUpdate.first { page ->
                    currentUpdate.all { it !in happensBefore[page].orEmpty() }
                }
                currentUpdate -= lastValue
            }

            lastValue
        }

    println("$first $second")
}
