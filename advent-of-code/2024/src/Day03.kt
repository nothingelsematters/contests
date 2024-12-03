fun main() {
    val instructions = """(mul\((\d+),(\d+)\)|do\(\)|don't\(\))""".toRegex()
        .findAll(getFullInput())
        .map { it.groupValues }
        .toList()

    val first = instructions.asSequence()
        .filter { it[0].startsWith("mul") }
        .sumOf { it[2].toInt() * it[3].toInt() }

    val second = instructions
        .fold(0 to true) { (current, flag), values ->
            when (values[0]) {
                "do()" -> current to true
                "don't()" -> current to false
                else -> {
                    val newValue = if (!flag) current else current + values[2].toInt() * values[3].toInt()
                    newValue to flag
                }
            }
        }
        .first

    println("$first $second")
}
