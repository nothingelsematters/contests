fun gcdOfStrings(l: String, r: String): String {
    for (i in minOf(l.length, r.length) downTo 1) {
        if (l.length % i != 0
            || r.length % i != 0
            || l.substring(0 until i) != r.substring(0 until i)
            || l.asSequence().chunked(i).distinct().count() != 1
            || r.asSequence().chunked(i).distinct().count() != 1
        ) continue

        return l.substring(0 until i)
    }

    return ""
}
