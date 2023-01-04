package leetcode

func minimumRounds(tasks []int) int {
	counts := make(map[int]int)

	for _, task := range tasks {
		elem, ok := counts[task]
		if ok {
			counts[task] = elem + 1
		} else {
			counts[task] = 1
		}
	}

	rounds := 0

	for _, count := range counts {
		if count == 1 {
			return -1
		}

		if count%3 == 0 {
			rounds += count / 3
		} else {
			rounds += count/3 + 1
		}
	}

	return rounds
}
