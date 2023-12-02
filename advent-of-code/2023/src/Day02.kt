data class Bag(var red: Int = 0, var green: Int = 0, var blue: Int = 0) {

    fun isSubset(rhs: Bag): Boolean =
        red <= rhs.red && green <= rhs.green && blue <= rhs.blue
}

data class Game(val id: Int, val bags: List<Bag>)

fun main() {
    val games = mapLines { line ->
        val (gameString, bagsString) = line.split(": ")
        val id = gameString.split(" ")[1].toInt()

        val bags = bagsString.splitToSequence("; ")
            .map { bagString ->
                val bag = Bag()

                bagString.splitToSequence(", ").forEach {
                    val (numberString, colour) = it.split(" ")
                    val number = numberString.toInt()

                    when (colour) {
                        "red" -> bag.red = number
                        "green" -> bag.green = number
                        "blue" -> bag.blue = number
                        else -> expect()
                    }
                }

                bag
            }
            .toList()

        Game(id, bags)
    }

    val target = Bag(12, 13, 14)
    val first = games.asSequence()
        .filter { game -> game.bags.all { it.isSubset(target) } }
        .sumOf { it.id }

    val second = games.sumOf { game ->
        val (r, g, b) = game.bags.fold(Bag()) { acc, bag ->
            Bag(
                maxOf(acc.red, bag.red),
                maxOf(acc.green, bag.green),
                maxOf(acc.blue, bag.blue),
            )
        }
        r * g * b
    }

    println("$first $second")
}
