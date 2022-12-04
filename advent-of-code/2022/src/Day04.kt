fun main() {
    val assignments = mapLines { it.toInts(",", "-") }

    val first = assignments.count { (a, b, l, r) -> a <= l && r <= b || l <= a && b <= r }
    val second = assignments.count { (a, b, l, r) -> a in l..r || b in l..r || l in a..b || r in a..b }

    println("$first $second")
}
