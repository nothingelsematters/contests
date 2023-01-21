fun restoreIpAddresses(s: String): List<String> = buildList { recursiveRestoreIpAddresses(s,this) }

fun recursiveRestoreIpAddresses(
    s: String,
    result: MutableList<String>,
    indices: MutableList<Int> = mutableListOf(0),
) {
    for (i in 1..3) {
        val from = indices.lastOrNull() ?: 0
        if (from + i > s.length) continue

        val substring = s.substring(from, from + i)

        if (substring.length > 1 && substring.startsWith('0') || substring.toIntOrNull() !in 0..255) {
            continue
        }

        indices += from + i

        if (indices.size != 5) {
            recursiveRestoreIpAddresses(s, result, indices)
        } else if (from + i == s.length) {
            val current = indices
                .asSequence()
                .windowed(2)
                .map { (l, r) -> s.substring(l, r) }
                .joinToString(".")
            result += current
        }

        indices.removeLast()
    }
}
