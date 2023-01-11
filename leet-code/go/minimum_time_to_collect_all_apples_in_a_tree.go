package leetcode

func minTime(n int, edges [][]int, hasApple []bool) int {
	graph := make(map[int][]int)

	for _, edge := range edges {
		for i := range edge {
			children, ok := graph[edge[i]]
			second := edge[(i+1)%len(edge)]

			if ok {
				graph[edge[i]] = append(children, second)
			} else {
				graph[edge[i]] = []int{second}
			}
		}
	}

	_, result := minTimeInternal(graph, make(map[int]bool), hasApple, 0)
	return result
}

func minTimeInternal(graph map[int][]int, visited map[int]bool, hasApple []bool, current int) (bool, int) {
	_, used := visited[current]
	if used {
		return false, 0
	}
	visited[current] = true

	currentApple := hasApple[current]
	children, ok := graph[current]

	if !ok {
		return currentApple, 0
	}

	result := 0

	for _, child := range children {
		childApple, childResult := minTimeInternal(graph, visited, hasApple, child)
		if childApple {
			currentApple = true
			result += childResult + 2
		}
	}

	return currentApple, result
}
