private fun part1(diskMap: List<Int>): Long {
    val storage = mutableListOf<Int>()
    diskMap.forEachIndexed { i, number ->
        repeat(number) {
            storage += if (i % 2 == 0) i / 2 else -1
        }
    }

    var nextSpace = storage.indexOf(-1)
    while (nextSpace < storage.size) {
        while (storage.last() == -1) storage.removeLast()
        storage[nextSpace++] = storage.removeLast()

        nextSpace += storage.subList(nextSpace, storage.size).indexOfOrNull(-1) ?: break
    }

    return storage.asSequence()
        .mapIndexed { index, value -> index.toLong() * value }
        .sum()
}

private fun part2(initialDiskMap: List<Int>): Long {
    val diskMap = initialDiskMap.asSequence()
        .mapIndexed { i, value -> (if (i % 2 == 0) i / 2 else -1) to value }
        .toMutableList()

    for (i in diskMap.last().first downTo 1) {
        val fileIndex = diskMap.indexOfFirst { it.first == i }
        val spaceIndex = diskMap.asSequence()
            .withIndex()
            .filter { it.value.first == -1 && it.value.second >= diskMap[fileIndex].second }
            .map { it.index }
            .firstOrNull()
            ?: continue

        if (spaceIndex > fileIndex) continue

        val spaceSize = diskMap[spaceIndex].second
        val file = diskMap[fileIndex]

        diskMap[fileIndex] = -1 to diskMap[fileIndex].second
        diskMap[spaceIndex] = file
        if (spaceSize != file.second) diskMap.add(spaceIndex + 1, -1 to spaceSize - file.second)

        // merge spaces
        var j = 0
        while (j < diskMap.size - 1) {
            if (diskMap[j].first == -1 && diskMap[j + 1].first == -1) {
                diskMap[j] = -1 to diskMap[j].second + diskMap[j + 1].second
                diskMap.removeAt(j + 1)
            } else {
                j++
            }
        }
    }

    return diskMap
        .fold(0 to 0L) { (prefix, acc), (id, size) ->
            prefix + size to
                if (id == -1) acc
                else acc + (prefix..<prefix + size).sumOf { it.toLong() * id }
        }
        .second
}

fun main() {
    val diskMap = readln().map { it.toString().toInt() }

    val first = part1(diskMap)
    val second = part2(diskMap)

    println("$first $second")
}
