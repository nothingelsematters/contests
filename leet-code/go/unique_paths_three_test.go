package leetcode

import "testing"

func TestUniquePathsIII(t *testing.T) {
	grid := [][]int{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, -1}}
	actual := uniquePathsIII(grid)

	if actual != 2 {
		t.Error(actual)
	}
}
