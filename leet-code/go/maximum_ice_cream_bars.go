package leetcode

import "sort"

func maxIceCream(costs []int, coins int) int {
	sort.Ints(costs)
	bars := 0

	for _, cost := range costs {
		if cost > coins {
			break
		}

		coins -= cost
		bars++
	}

	return bars
}
