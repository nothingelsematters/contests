fun convert(s: String, numRows: Int): String {
    if (numRows <= 1) return s

    val stringBuilders = List(numRows) { StringBuilder() }

    s.asSequence().chunked(numRows * 2 - 2).forEach { chunk ->
        chunk.asSequence()
            .take(numRows)
            .forEachIndexed { index, i -> stringBuilders[index].append(i) }

        if (chunk.size > numRows) {
            chunk.subList(numRows, chunk.size)
                .forEachIndexed { index, i -> stringBuilders[numRows - index - 2].append(i) }
        }
    }

    return stringBuilders.reduce { l, r -> l.append(r) }.toString()
}
