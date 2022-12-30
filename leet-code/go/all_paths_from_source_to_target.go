package leetcode

func allPathsSourceTarget(graph [][]int) [][]int {
	return allPaths(graph, make(map[int][][]int), 0, len(graph)-1)
}

func allPaths(graph [][]int, visited map[int][][]int, current int, to int) [][]int {
	if current == to {
		return [][]int{{to}}
	}

	met, ok := visited[current]
	if ok {
		return met
	}

	result := [][]int{}
	for _, v := range graph[current] {
		v_result := allPaths(graph, visited, v, to)

		for _, u := range v_result {
			result = append(result, append([]int{current}, u...))
		}
	}

	visited[current] = result
	return result
}
