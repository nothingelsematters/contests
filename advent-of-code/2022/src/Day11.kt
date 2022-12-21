private data class Monkey(
    val items: MutableList<Long>,
    val operation: (Long) -> Long,
    val divider: Int,
    val throwItem: (Long) -> Int,
)

private fun monkeyBusiness(monkeys: List<Monkey>, rounds: Int, transform: (Long) -> Long = { it }): Long {
    val processed = MutableList(monkeys.size) { 0L }
    val divider = monkeys.asSequence().map { it.divider }.reduce(Int::times)

    repeat(rounds) {
        monkeys.forEachIndexed { i, (items, operation, _, throwItem) ->
            processed[i] += items.size.toLong()
            items.forEach {
                val newItem = transform(operation(it)) % divider
                monkeys[throwItem(newItem)].items += newItem
            }
            items.clear()
        }
    }

    return processed.asSequence().sortedDescending().take(2).reduce(Long::times)
}

fun main() {
    val monkeys = mapBlocks { block ->
        val trimmed = block.map { it.trim() }

        val items = trimmed[1].removePrefix("Starting items: ").toLongs()
        val operation = trimmed[2].removePrefix("Operation: new = old ").let {
            val (operationString, operandString) = it.split(' ')

            val operation = operationString.toOperation()
            when (operandString) {
                "old" -> { x: Long -> operation(x, x) }
                else -> { x: Long -> operation(x, operandString.toLong()) }
            }
        }
        val (divisible, trueThrow, falseThrow) = trimmed[3..5].map { it.split(' ').last().toInt() }

        Monkey(items, operation, divisible) { x: Long -> if (x % divisible == 0L) trueThrow else falseThrow }
    }

    val monkeysDeepCopy = monkeys.map { it.copy(items = it.items.toMutableList()) }
    println(monkeyBusiness(monkeysDeepCopy, 20) { it / 3 })
    println(monkeyBusiness(monkeys, 10_000))
}
