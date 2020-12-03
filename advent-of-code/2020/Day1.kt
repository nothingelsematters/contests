fun main() {
    val list = mapLines { it.toInt() }

    var first = 0
    var second = 0

    for (i in list) {
        for (j in list) {
            if (i + j == 2020) {
                first = i * j
            }

            for (k in list) {
                if (i + j + k == 2020) {
                    second = i * j * k
                }
            }
        }
    }

    println("$first $second")
}
