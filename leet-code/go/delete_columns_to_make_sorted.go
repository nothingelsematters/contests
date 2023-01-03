package leetcode

func minDeletionSize(strs []string) int {
	count := 0

	for j := range strs[0] {
		for i := range strs[1:] {
			if strs[i][j] > strs[i+1][j] {
				count++
				break
			}
		}
	}

	return count
}
