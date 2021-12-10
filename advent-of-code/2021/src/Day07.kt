import kotlin.math.abs

private fun findMinFuel(fuel: List<Int>, wasting: (position: Int) -> Int) =
    (fuel.minOrNull().expect()..fuel.maxOrNull().expect())
        .minOfOrNull { position -> fuel.sumOf { wasting(abs(position - it)) } }
        .expect()

private fun part1(fuel: List<Int>) = findMinFuel(fuel) { it }

private fun part2(fuel: List<Int>) = findMinFuel(fuel) { it * (it + 1) / 2 }

fun main() {
    val fuel = readln().toInts(",")
    println(part1(fuel))
    println(part2(fuel))
}
