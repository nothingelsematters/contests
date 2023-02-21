func singleNonDuplicate(_ nums: [Int]) -> Int {
    var l = 0
    var r = nums.count - 1

    while l <= r {
        let c = (l + r) / 2

        if (c == 0 || nums[c - 1] != nums[c]) && (c == nums.count - 1 || nums[c + 1] != nums[c]) {
            return nums[c]
        }

        if c % 2 == 0 && nums[c] == nums[c + 1] || c % 2 != 0 && nums[c - 1] == nums[c] {
            l = c + 1
        } else {
            r = c - 1
        }
    }

    return nums[l]
}
