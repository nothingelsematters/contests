fun main() {
    val foods = mapLines {
        val (ingredients, other) = it.split(" (contains ")
        ingredients.splitToSequence(' ').toSet() to other.dropLast(1).split(", ").toSet()
    }

    val venom = foods.asSequence()
        .flatMap { (ingredients, poison) -> poison.asSequence().map { it to ingredients } }
        .groupBy({ it.first }) { it.second }
        .mapValues { it.value.reduce(Set<String>::intersect).toMutableSet() }

    val safe = foods.asSequence().map { it.first }.reduce(Set<String>::union) - venom.values.reduce(Set<String>::union)

    val first = foods.asSequence().map { (ins, _) -> safe.count { ins.contains(it) } }.sum()

    while (venom.any { it.value.size != 1 }) {
        val values = venom.asSequence().filter { it.value.size == 1 }.map { (k, v) -> k to v.single() }
        for (k in venom.keys - values.map { it.first }) {
            venom[k]!!.removeAll(values.map { it.second })
        }
    }

    val second = venom.asSequence().sortedBy { it.key }.map { it.value.single() }.joinToString(",")

    println("$first $second")
}
