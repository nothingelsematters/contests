fun shuffle(nums: IntArray, n: Int): IntArray =
    nums.asSequence()
        .zip(nums.asList().subList(n, nums.size).asSequence())
        .flatMap { (a, b) -> sequenceOf(a, b) }
        .toList()
        .toIntArray()
