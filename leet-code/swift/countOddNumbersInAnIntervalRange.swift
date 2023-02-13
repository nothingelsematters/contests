func countOdds(_ low: Int, _ high: Int) -> Int {
    var result = (high - low) / 2
    if (high % 2 != 0 || low % 2 != 0) {
        return result + 1
    } else {
        return result
    }
}
