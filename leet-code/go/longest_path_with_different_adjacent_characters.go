package leetcode

import "sort"

func longestPath(parent []int, s string) int {
	if len(parent) == 0 {
		return 0
	}

	children := make([][]int, len(parent))
	for i, p := range parent[1:] {
		children[p] = append(children[p], i+1)
	}

	rooted, unrooted := dfs(children, s, 0)
	if rooted > unrooted {
		return rooted
	}
	return unrooted
}

func dfs(children [][]int, s string, current int) (int, int) {
	childrenResults := []int{}
	maxNotCurrent := 1

	for _, child := range children[current] {
		first, second := dfs(children, s, child)
		if maxNotCurrent < first {
			maxNotCurrent = first
		}
		if maxNotCurrent < second {
			maxNotCurrent = second
		}

		if s[child] != s[current] {
			childrenResults = append(childrenResults, first)
		}
	}

	sort.Sort(sort.Reverse(sort.IntSlice(childrenResults)))

	if len(childrenResults) == 0 {
		return 1, maxNotCurrent
	}

	withCurrent := childrenResults[0] + 1

	if len(childrenResults) == 1 {
		return withCurrent, maxNotCurrent
	}

	curve := withCurrent + childrenResults[1]
	if maxNotCurrent < curve {
		return withCurrent, curve
	}
	return withCurrent, maxNotCurrent
}
