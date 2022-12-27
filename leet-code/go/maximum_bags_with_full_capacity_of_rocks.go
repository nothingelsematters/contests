package leetcode

import "sort"

func maximumBags(capacity []int, rocks []int, additionalRocks int) int {
	for i := range capacity {
		capacity[i] -= rocks[i]
	}

	sort.Ints(capacity)

	for i, v := range capacity {
		if additionalRocks == 0 || additionalRocks < v {
			return i
		}

		additionalRocks -= v
	}

	return len(capacity)
}
