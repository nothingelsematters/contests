private val memory = mutableListOf(0, 1, 1)

fun tribonacci(n: Int): Int {
    if (memory.lastIndex < n) {
        memory += (3 downTo 1).sumBy { tribonacci(n - it) }
    }
    return memory[n]
}
