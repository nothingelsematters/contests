package leetcode

import "unicode"

func detectCapitalUse(word string) bool {
	runes := []rune(word)

	if len(runes) < 2 {
		return true
	}

	if unicode.IsUpper(runes[0]) && unicode.IsUpper(runes[1]) {
		for _, r := range runes[2:] {
			if unicode.IsLower(r) {
				return false
			}
		}
	} else {
		for _, r := range runes[1:] {
			if unicode.IsUpper(r) {
				return false
			}
		}
	}

	return true
}
