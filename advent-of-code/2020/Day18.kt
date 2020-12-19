/**
 * Simply ran my LALR parser generator (https://github.com/nothingelsematters/lalr-generator/)
 * on files Day18Part1.gr and Day18Part2.gr and used the result.
 */
fun main() {
    val expressions = readText()
    val first = DayEighteenPartOneParser().parse(expressions.byteInputStream())
    val second = DayEighteenPartTwoParser().parse(expressions.byteInputStream())
    println("$first $second")
}
