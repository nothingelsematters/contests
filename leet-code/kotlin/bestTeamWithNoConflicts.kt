fun bestTeamScore(scores: IntArray, ages: IntArray): Int =
    ages.asSequence()
        .zip(scores.asSequence())
        .sortedWith(compareBy<Pair<Int, Int>> { it.first }.thenComparingInt { it.second })
        .map { it.second }
        .toList()
        .let(::dfs)

fun dfs(
    scores: List<Int>,
    maxScore: Int = 0,
    memory: MutableMap<Pair<Int, Int>, Int> = mutableMapOf(),
): Int {
    val score = scores.firstOrNull() ?: return 0
    val sublist = scores.subList(1, scores.size)
    val skipped = memory.getOrPut(maxScore to sublist.size) { dfs(sublist, maxScore, memory) }

    return if (score < maxScore) {
        skipped
    } else {
        val taken = memory.getOrPut(score to sublist.size) { dfs(sublist, score, memory) }
        maxOf(skipped, score + taken)
    }
}
