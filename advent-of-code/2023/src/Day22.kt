private data class Point(val x: Int, val y: Int, val z: Int)

private class Brick(val from: Point, val to: Point) {

    val type = when {
        from.z != to.z -> Type.Z
        from.y != to.y -> Type.Y
        else -> Type.X
    }

    val interval = when (type) {
        Type.X -> Interval(from.x, to.x)
        Type.Y -> Interval(from.y, to.y)
        Type.Z -> Interval(from.z, to.z)
    }

    enum class Type { X, Y, Z }
}

private inline fun <reified T> MutableList<MutableList<T>>.appendAdd(index: Int, element: T) {
    while (index > lastIndex) this += mutableListOf<T>()
    this[index] += element
}

private fun fallenBrickLayer(fallenBricks: List<List<Brick>>, brick: Brick): Pair<Int, Int> {
    val layer = 1 + fallenBricks.indexOfLast { layer ->
        layer.any { fallenBrick ->
            val (a, b) = if (brick.type <= fallenBrick.type) brick to fallenBrick else fallenBrick to brick

            when (a.type to b.type) {
                Brick.Type.X to Brick.Type.X -> a.from.y == b.from.y && !a.interval.intersect(b.interval).isEmpty()
                Brick.Type.Y to Brick.Type.Y -> a.from.x == b.from.x && !a.interval.intersect(b.interval).isEmpty()
                Brick.Type.X to Brick.Type.Y -> a.from.y in b.interval && b.from.x in a.interval
                Brick.Type.X to Brick.Type.Z -> a.from.y == b.from.y && b.from.x in a.interval
                Brick.Type.Y to Brick.Type.Z -> a.from.x == b.from.x && b.from.y in a.interval
                Brick.Type.Z to Brick.Type.Z -> a.from.x == b.from.x && a.from.y == b.from.y
                else -> expect()
            }
        }
    }
    return if (brick.type != Brick.Type.Z) layer to layer else layer to layer + brick.interval.size.toInt() - 1
}

private fun fallenBricks(bricks: List<Brick>): Pair<List<List<Brick>>, List<List<Brick>>> {
    val low = mutableListOf<MutableList<Brick>>()
    val high = mutableListOf<MutableList<Brick>>()

    bricks.asSequence().sortedBy { it.from.z }.forEach { brick ->
        val (l, h) = fallenBrickLayer(high, brick)
        low.appendAdd(l, brick)
        high.appendAdd(h, brick)
    }

    return low to high
}

private fun disintegratedBricksFallen(low: List<List<Brick>>, high: List<List<Brick>>, highIndex: Index2): Int {
    val highCopy = high.take(highIndex.i + 1).mutableDeepCopy().toMutableList()
    highCopy[highIndex.i].removeAt(highIndex.j)

    return low.indexed2Sequence().count { (lowIndex, brick) ->
        if (lowIndex.i < highIndex.i + 1) {
            if (brick.type == Brick.Type.Z) {
                val newHighIndex = lowIndex.i + brick.interval.size.toInt() - 1
                if (newHighIndex >= highIndex.i + 1) {
                    highCopy.appendAdd(newHighIndex, brick)
                }
            }
            return@count false
        }

        val (l, h) = fallenBrickLayer(highCopy, brick)
        highCopy.appendAdd(h, brick)
        l != lowIndex.i
    }
}

fun main() {
    val bricks = mapLines { line ->
        val (a, b) = line.splitToSequence('~')
            .map { it.toInts() }
            .map { (x, y, z) -> Point(x, y, z) }
            .toList()
        Brick(a, b)
    }

    val (low, high) = fallenBricks(bricks)
    val first = high.indexed2Sequence().count { (i, _) -> disintegratedBricksFallen(low, high, i) == 0 }
    val second = high.indexed2Sequence().sumOf { (i, _) -> disintegratedBricksFallen(low, high, i) }

    println("$first $second")
}
