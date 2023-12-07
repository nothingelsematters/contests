private enum class HandType {
    HighCard,
    Pair,
    TwoPairs,
    Three,
    FullHouse,
    Four,
    Five;

    companion object {
        fun ofCounts(counts: Map<Int, Int>): HandType = when {
            5 in counts -> Five
            4 in counts -> Four
            3 in counts && 2 in counts -> FullHouse
            3 in counts -> Three
            counts[2]?.let { it >= 2 } == true -> TwoPairs
            2 in counts -> Pair
            else -> HighCard
        }
    }
}

private class Hand(
    cardsString: String,
    val bid: Int,
    cardStrength: String,
    countCards: (cards: List<Int>) -> Map<Int, Int>,
) : Comparable<Hand> {

    private val cards: List<Int> = cardsString.map { cardStrength.indexOf(it) }

    private val strength: HandType = HandType.ofCounts(countCards(cards))

    private val comparator = Comparator.comparing { it: Hand -> it.strength }
        .thenComparing { o1, o2 ->
            o1.cards.asSequence()
                .zip(o2.cards.asSequence())
                .find { (a, b) -> a != b }
                ?.let { (a, b) -> a.compareTo(b) }
                ?: 0
        }

    override fun compareTo(other: Hand): Int = comparator.compare(this, other)
}

private fun getTotalWinnings(
    lines: List<Pair<String, Int>>,
    cardStrength: String,
    countCards: (cards: List<Int>) -> Map<Int, Int>,
): Int =
    lines.asSequence()
        .map { (cards, bid) -> Hand(cards, bid, cardStrength, countCards) }
        .sorted()
        .withIndex()
        .sumOf { (index, hand) -> (index + 1) * hand.bid }

fun main() {
    val lines = mapLines {
        val (cards, bidString) = it.split(" ")
        cards to bidString.toInt()
    }

    val first = getTotalWinnings(lines, "23456789TJQKA") { cards ->
        cards.groupingBy { it }
            .eachCount()
            .asSequence()
            .groupingBy { it.value }
            .eachCount()
    }

    val second = getTotalWinnings(lines, "J23456789TQKA") { cards ->
        val cardCounts = cards.groupingBy { it }.eachCount().toMutableMap()
        val js = cardCounts.remove(0) ?: 0

        val frequent = cardCounts.maxByOrNull { it.value }?.key
        if (frequent == null) {
            cardCounts[0] = js
        } else {
            cardCounts[frequent] = cardCounts[frequent]!! + js
        }

        cardCounts.asSequence().groupingBy { it.value }.eachCount()
    }

    println("$first $second")
}
