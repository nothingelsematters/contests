package leetcode

import "strings"

func wordPattern(pattern string, s string) bool {
	if strings.Count(s, " ")+1 != len(pattern) {
		return false
	}

	letterToWord := make(map[byte]string)
	words := make(map[string]bool)

	for i, part := range strings.Split(s, " ") {
		letter := pattern[i]
		word, letter_word_ok := letterToWord[letter]
		_, word_ok := words[part]

		if letter_word_ok && word != part || !letter_word_ok && word_ok {
			return false
		}

		if !letter_word_ok {
			letterToWord[letter] = part
			words[part] = true
		}
	}

	return true
}
