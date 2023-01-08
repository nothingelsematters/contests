package leetcode

import "math"

func maxPoints(points [][]int) int {
	if len(points) < 3 {
		return len(points)
	}

	max := 2

	for i, first := range points[:len(points)-2] {
		for j, second := range points[i+1 : len(points)-1] {
			count := 2

			if first[0] == second[0] {
				for _, third := range points[i+j+2:] {
					if third[0] == first[0] {
						count++
					}
				}
			} else {
				first_x, first_y := float64(first[0]), float64(first[1])
				second_x, second_y := float64(second[0]), float64(second[1])

				a := (second_y - first_y) / (second_x - first_x)
				b := first_y - first_x*a

				for _, third := range points[i+j+2:] {
					third_x, third_y := float64(third[0]), float64(third[1])
					if math.Abs(a*third_x+b-third_y) < 1e-6 {
						count++
					}
				}
			}

			if count > max {
				max = count
			}
		}
	}

	return max
}
