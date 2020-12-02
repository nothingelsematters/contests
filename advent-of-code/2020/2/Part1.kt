fun main() {
    val regex = Regex("(?<min>\\d+)-(?<max>\\d+) (?<char>\\w): (?<pass>\\w+)")
    println(
        System.`in`.bufferedReader().useLines {
            it.filter {
                val (min, max, char, pass) = regex.matchEntire(it)!!.destructured
                pass.asSequence().filter { it.toString() == char }.count() in min.toInt()..max.toInt()
            }
            .count()
        }
    )
}
