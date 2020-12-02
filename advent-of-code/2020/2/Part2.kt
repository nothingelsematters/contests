fun main() {
    val regex = Regex("(?<min>\\d+)-(?<max>\\d+) (?<char>\\w): (?<pass>\\w+)")
    println(
        System.`in`.bufferedReader().useLines {
            it.filter {
                val (min, max, char, pass) = regex.matchEntire(it)!!.destructured
                (pass[min.toInt() - 1].toString() == char) != (pass[max.toInt() - 1].toString() == char)
            }
            .count()
        }
    )
}
