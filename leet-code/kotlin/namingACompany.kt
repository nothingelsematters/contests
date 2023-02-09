fun distinctNames(ideas: Array<String>): Long {
    val suffixes = ideas.asSequence()
        .groupingBy{ it[0] }
        .fold({ _, _ -> mutableSetOf<String>() }) { _, set, i -> set.also { set += i.substring(1) } }

    return suffixes.asSequence()
        .flatMap { i -> suffixes.asSequence().map { it to i } }
        .filter { (l, r) -> l.key != r.key }
        .map { (l, r) -> l.value to r.value }
        .sumOf { (l, r) ->
            val intersected = (l intersect r).size
            ((l.size - intersected) * (r.size - intersected)).toLong()
        }
}
