data class Basket(var type: Int, var size: Int)

fun totalFruit(fruits: IntArray): Int {
    var firstType = fruits.firstOrNull() ?: return 0

    val position = fruits.indexOfFirst { it != firstType }
    if (position == -1) return fruits.size

    var first = Basket(firstType, position)
    var second = Basket(fruits[position], 1)
    var last = second.copy()

    var max = 0

    fruits.asList().subList(position + 1, fruits.size).forEach {
        when (it) {
            first.type -> {
                if (last.type == it) {
                    last.size++
                } else {
                    last = Basket(it, 1)
                }

                first.size++
            }
            second.type -> {
                if (last.type == it) {
                    last.size++
                } else {
                    last = Basket(it, 1)
                }

                second.size++
            }
            else -> {
                max = maxOf(max, first.size + second.size)
                first = last
                second = Basket(it, 1)
                last = second.copy()
            }
        }
    }

    return maxOf(max, first.size + second.size)
}
