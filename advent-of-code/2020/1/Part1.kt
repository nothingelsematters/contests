fun main() {
    val intSet = mutableSetOf<Int>()

    while (true) {
        val value = (readLine() ?: break).toInt()
        if (intSet.contains(value)) {
            println(value * (2020 - value))
            return
        }
        intSet.add(2020 - value)
    }
}
