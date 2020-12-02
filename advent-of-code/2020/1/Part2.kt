fun main() {
    val list = mutableListOf<Int>()

    while (true) {
        list.add((readLine() ?: break).toInt())
    }

    for (i in list) {
        for (j in list) {
            for (k in list) {
                if (i + j + k == 2020) {
                    println(i * j * k)
                    return
                }
            }
        }
    }
}
