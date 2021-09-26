fun toCamelCase(str: String): String = str.splitToSequence('-', '_')
    .filter { it.isNotEmpty() }
    .withIndex()
    .flatMap { (index, word) ->
        sequenceOf(word[0].let { if (index == 0) it else it.uppercase() } ) + word.asSequence().drop(1)
    }
    .joinToString("")
