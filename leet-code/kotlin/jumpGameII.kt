fun jump(nums: IntArray): Int {
    var result = 0
    var end = 0
    var distance = 0

    (0 until nums.lastIndex).forEach { i ->
        distance = maxOf(distance, i + nums[i])

        if (i == end) {
            result++
            end = distance
        }
    }

    return result
}
