private val RANGE = 200_000_000_000_000.0..400_000_000_000_000.0

private fun kb(point: Point, trajectory: Point): Pair<Double, Double> {
    // y = kx + b
    val k = trajectory.y.toDouble() / trajectory.x // no x = 0 => never mind
    val b = point.y - k * point.x
    return k to b
}

private fun twoAxesIntersections(trajectories: List<Pair<Point, Point>>): Int =
    trajectories.sumOfIndexed { i, (point1, trajectory1) ->
        trajectories.asSequence().take(i).count { (point2, trajectory2) ->
            val (k1, b1) = kb(point1, trajectory1)
            val (k2, b2) = kb(point2, trajectory2)

            // k1 x + b1 = k2 x + b2
            // x = (b2 - b1) / (k1 - k2)
            val x = (b2 - b1) / (k1 - k2)
            val y = k1 * x + b1

            x.isFinite() && x in RANGE && y in RANGE &&
                trajectory1.x > 0 == x - point1.x > 0 && trajectory2.x > 0 == x - point2.x > 0
        }
    }

private fun findIntersectionLine(trajectories: List<Pair<Point, Point>>) {
    println("Go to https://microsoft.github.io/z3guide/playground/Freeform%20Editing/ with the following input:")

    println("(declare-const fx Int)")
    println("(declare-const fy Int)")
    println("(declare-const fz Int)")
    println("(declare-const fdx Int)")
    println("(declare-const fdy Int)")
    println("(declare-const fdz Int)")

    trajectories.forEachIndexed { i, (p, t) ->
        val (x, y, z) = p
        val (dx, dy, dz) = t

        println("(declare-const t_$i Int)")
        println("(assert (>= t_$i 0))")
        println("(assert (= (+ $x (* $dx t_$i)) (+ fx (* fdx t_$i))))")
        println("(assert (= (+ $y (* $dy t_$i)) (+ fy (* fdy t_$i))))")
        println("(assert (= (+ $z (* $dz t_$i)) (+ fz (* fdz t_$i))))")
    }

    println("(check-sat)")
    println("(get-model)")
}

fun main() {
    val trajectories = mapLines { line ->
        line.splitToSequence('@')
            .map { it.toLongs() }
            .map { (x, y, z) -> Point(x, y, z) }
            .toList()
            .toPair()
    }

    val first = twoAxesIntersections(trajectories)
    println(first)

    findIntersectionLine(trajectories)
}
