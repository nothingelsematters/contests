package leetcode

func canJump(nums []int) bool {
	max := 0

	for i, n := range nums {
		if i > max {
			return false
		}

		if max < i+n {
			max = i + n
		}
	}

	return true
}
