package leetcode

import "testing"

func TestMaximumBagsSample1(t *testing.T) {
	capacity := []int{2, 3, 4, 5}
	rocks := []int{1, 2, 4, 4}
	additionalRocks := 2

	if maximumBags(capacity, rocks, additionalRocks) != 3 {
		println(maximumBags(capacity, rocks, additionalRocks))
		t.Error()
	}
}

func TestMaximumBagsSample2(t *testing.T) {
	capacity := []int{10, 2, 2}
	rocks := []int{2, 2, 0}
	additionalRocks := 100

	if maximumBags(capacity, rocks, additionalRocks) != 3 {
		println(maximumBags(capacity, rocks, additionalRocks))
		t.Error()
	}
}

func TestMaximumBagsWrongAnswer25(t *testing.T) {
	capacity := []int{91, 54, 63, 99, 24, 45, 78}
	rocks := []int{35, 32, 45, 98, 6, 1, 25}
	additionalRocks := 17

	if maximumBags(capacity, rocks, additionalRocks) != 1 {
		println(maximumBags(capacity, rocks, additionalRocks))
		t.Error()
	}
}
