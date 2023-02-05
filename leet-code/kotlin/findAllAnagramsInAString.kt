fun findAnagrams(s: String, p: String): List<Int> {
    if (s.length < p.length) return emptyList()

    val needed = p.asSequence().groupingBy { it }.eachCount()
    val current = s.substring(0..p.lastIndex)
        .asSequence()
        .groupingBy { it }
        .eachCount()
        .toMutableMap()
    val result = mutableListOf<Int>()

    if (current == needed) result += 0

    s.asSequence()
        .zip(s.substring(p.length).asSequence())
        .withIndex()
        .forEach { (i, lr) ->
            val (l, r) = lr

            when (val first = current[l]!!) {
                1 -> current.remove(l)
                else -> current[l] = first - 1
            }
            current[r] = current.getOrDefault(r, 0) + 1

            if (current == needed) result += i + 1
        }

    return result
}
