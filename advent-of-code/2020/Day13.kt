fun main() {
    val time = readLine()!!.toInt()
    val ids = readLine()!!.splitToSequence(',')
        .withIndex()
        .filter { (_, it) -> it != "x" }
        .map { (id, it) -> id.toLong() to it.toLong() }
        .toList()

    val first = ids.asSequence()
        .map { (_, id) ->
            val wait = id * (time / id + 1) - time
            wait to id * wait
        }
        .minByOrNull { it.first }!!
        .second

    var step = ids.first().second
    var second = 0L

    ids.drop(1).forEach { (index, bus) ->
        val remainder = (index * bus - index) % bus
        while (second % bus != remainder) second += step
        step *= bus
    }

    println("$first $second")
}
