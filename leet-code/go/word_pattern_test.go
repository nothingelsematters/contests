package leetcode

import "testing"

func TestWordPattern(t *testing.T) {
	pattern := "abba"
	s := "dog cat cat dog"
	actual := wordPattern(pattern, s)

	if !actual {
		t.Error(actual)
	}
}
