fun <T> mapLines(mapper: (String) -> T): List<T> =
    System.`in`.bufferedReader().useLines { lines -> lines.map(mapper).toList() }
