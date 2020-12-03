fun main() {
    val list = System.`in`.bufferedReader().useLines { lines -> lines.map { it.toInt() }.toList() }

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
