data class Policy(val min: Int, val max: Int, val char: Char, val password: String)

fun main() {
    val regex = Regex("(?<min>\\d+)-(?<max>\\d+) (?<char>\\w): (?<pass>\\w+)")
    val passwords = System.`in`.bufferedReader().useLines {
        it.map {
            val (min, max, char, pass) = regex.matchEntire(it)!!.destructured
            Policy(min.toInt(), max.toInt(), char[0], pass)
        }
        .toList()
    }

    val first = passwords.asSequence()
        .filter { policy -> policy.password.asSequence().filter { it == policy.char }.count() in policy.min..policy.max }
        .count()
    val second = passwords.asSequence()
        .filter { (it.password[it.min - 1] == it.char) != (it.password[it.max - 1] == it.char) }
        .count()

    println("$first $second")
}
