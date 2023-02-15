func addToArrayForm(_ num: [Int], _ k: Int) -> [Int] {
    var result = num.reversed().map { $0 }
    var left = k

    for i in result.indices {
        let local = left + result[i]
        result[i] = local % 10
        left = local / 10
    }

    while left != 0 {
        result.append(left % 10)
        left /= 10
    }

    return result.reversed()
}
