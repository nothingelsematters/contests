package leetcode

import "testing"

func TestJumpGameSample1(t *testing.T) {
	nums := []int{2, 3, 1, 1, 4}
	if !canJump(nums) {
		t.Error()
	}
}

func TestJumpGameSample2(t *testing.T) {
	nums := []int{3, 2, 1, 0, 4}
	if canJump(nums) {
		t.Error()
	}
}
