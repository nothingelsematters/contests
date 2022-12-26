package leetcode

import "testing"

func TestSample1(t *testing.T) {
	nums := []int{2, 3, 1, 1, 4}
	if !canJump(nums) {
		t.Error()
	}
}

func TestSample2(t *testing.T) {
	nums := []int{3, 2, 1, 0, 4}
	if canJump(nums) {
		t.Error()
	}
}
