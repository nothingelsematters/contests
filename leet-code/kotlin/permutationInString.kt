fun checkInclusion(s1: String, s2: String): Boolean {
    if (s1.length > s2.length) return false

    val needed = s1.asSequence().groupingBy { it }.eachCount()
    val current = s2.substring(0..s1.lastIndex)
        .asSequence()
        .groupingBy { it }
        .eachCount()
        .toMutableMap()

    s2.asSequence()
        .zip(s2.substring(s1.length).asSequence())
        .forEach { (l, r) ->
            if (current == needed) return true

            when (val first = current[l]!!) {
                1 -> current.remove(l)
                else -> current[l] = first - 1
            }
            current[r] = current.getOrDefault(r, 0) + 1
        }

    return current == needed
}
