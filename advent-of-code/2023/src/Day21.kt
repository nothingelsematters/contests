import kotlin.math.abs

private fun readMap(): Pair<List<List<Boolean>>, Index2> {
    val lines = mapLines { it.toList() }

    var start = Index2(0, 0)
    val map = lines.mapIndexed { i, list ->
        list.mapIndexed { j, c ->
            if (c == 'S') start = Index2(i, j)
            c == '#'
        }
    }

    return map to start
}

private fun getReachable(map: List<List<Boolean>>, start: Index2, steps: Int): Long {
    val visited = mutableSetOf<Index2>()
    val q = ArrayDeque<Index2>()
    q += start

    var even = 0
    var odd = 0
    var other = 0

    while (q.isNotEmpty()) {
        val index = q.removeFirst()

        if (map.getOrNull(index) != false || index in visited) continue
        visited += index

        val distance = abs(index.i - map.size / 2) + abs(index.j - map.size / 2)
        when {
            distance > map.size / 2 -> other++
            distance % 2 == 0 -> even++
            else -> odd++
        }

        q += Direction.entries.map { index + it.index2 }
    }

    val x = steps.toLong() / map.size
    return (x + 1) * (x + 1) * odd + x * x * even + x * (x + 1) * other
}

fun main() {
    val (map, start) = readMap()

    val first = getReachable(map, start, 64)
    val second = getReachable(map, start, 26501365)

    println("$first $second")
}
