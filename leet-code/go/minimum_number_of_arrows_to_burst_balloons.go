package leetcode

import "sort"

func findMinArrowShots(points [][]int) int {
	sort.Slice(points, func(i, j int) bool {
		return points[i][0] < points[j][0]
	})

	arrows := 0
	current_x2 := points[0][1]
	for _, point := range points[1:] {
		if point[0] > current_x2 {
			arrows++
			current_x2 = point[1]
		} else if point[1] < current_x2 {
			current_x2 = point[1]
		}
	}

	return arrows + 1
}
