class LFUCache(val capacity: Int) {
    private val keyValue = HashMap<Int, Pair<Int, Int>>(capacity)
    private val frequencies = mutableMapOf<Int, MutableSet<Int>>()
    private var minimumFrequency = 0

    private fun updateMinimumFrequency(frequency: Int) {
        if (frequency == minimumFrequency && frequencies.getValue(frequency).size == 0) {
            minimumFrequency++
        }
    }

    private fun updateFrequency(key: Int, value: Int, currentFrequency: Int) {
        frequencies[currentFrequency]!! -= key
        frequencies.getOrPut(currentFrequency + 1) { LinkedHashSet() } += key
        keyValue[key] = value to currentFrequency + 1
        updateMinimumFrequency(currentFrequency)
    }

    operator fun get(key: Int): Int {
        val (value, frequency) = keyValue[key] ?: return -1
        updateFrequency(key, value, frequency)
        return value
    }

    operator fun set(key: Int, value: Int) {
        if (capacity < 1) return

        keyValue[key]?.let { (_, frequency) ->
            updateFrequency(key, value, frequency)
            return
        }

        if (keyValue.size == capacity) {
            val deletedKey = frequencies[minimumFrequency]!!.first()
            frequencies[minimumFrequency]!! -= deletedKey
            keyValue -= deletedKey
            updateMinimumFrequency(minimumFrequency)
        }

        minimumFrequency = 0
        keyValue[key] = value to 0
        frequencies.getOrPut(0) { LinkedHashSet() } += key
    }

    fun put(key: Int, value: Int) = set(key, value)
}
