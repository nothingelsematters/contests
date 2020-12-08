fun <T> mapLines(mapper: (String) -> T): MutableList<T> =
    System.`in`.bufferedReader().useLines { lines -> lines.map(mapper).toMutableList() }
