package leetcode

func countSubTrees(n int, edges [][]int, labels string) []int {
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

	result := make([]int, n)
	countSubTreesInternal(graph, labels, make(map[byte]int), result, 0)
	return result
}

func countSubTreesInternal(graph map[int][]int, labels string, visited map[byte]int, result []int, current int) {
	if result[current] != 0 {
		return
	}

	result[current] = -1
	visitedInitial := make(map[byte]int)
	for k, v := range visited {
		visitedInitial[k] = v
	}

	for _, child := range graph[current] {
		visitedCopy := make(map[byte]int)
		for k, v := range visitedInitial {
			visitedCopy[k] = v
		}

		countSubTreesInternal(graph, labels, visitedCopy, result, child)

		for k, v := range visitedCopy {
			_, ok := visited[k]
			if ok {
				visited[k] += v
			} else {
				visited[k] = v
			}
		}
	}

	label := labels[current]
	_, ok := visited[label]
	if ok {
		visited[label]++
	} else {
		visited[label] = 1
	}
	result[current] = visited[label]
}
