func canLoad(_ weights: [Int], _ days: Int, _ capacity: Int) -> Bool {
    var days = days
    var i = 0

    while i < weights.count {
        if weights[i] > capacity {
            return false
        }

        var current = weights[i]
        i += 1

        while i < weights.count && current + weights[i] <= capacity {
            current += weights[i]
            i += 1
        }

        days -= 1
    }

    return days >= 0
}

func shipWithinDays(_ weights: [Int], _ days: Int) -> Int {
    var l = 0
    var r = 1_000_000

    while l <= r {
        let c = (l + r) / 2

        if canLoad(weights, days, c) {
            r = c - 1
        } else {
            l = c + 1
        }
    }

    return l
}
