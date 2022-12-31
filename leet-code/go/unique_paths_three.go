package leetcode

type Cell struct {
	i, j int
}

func uniquePathsIII(grid [][]int) int {
	starting := Cell{0, 0}
	empty := 0

	for i := 0; i < len(grid); i++ {
		for j := 0; j < len(grid[i]); j++ {
			if grid[i][j] == 1 {
				starting = Cell{i, j}
			}
			if grid[i][j] == 0 {
				empty++
			}
		}
	}

	return internalUiquePaths(grid, empty, starting, make(map[Cell]bool))
}

func internalUiquePaths(grid [][]int, empty int, current Cell, used map[Cell]bool) int {
	if grid[current.i][current.j] == -1 {
		return 0
	}

	if grid[current.i][current.j] == 2 {
		if empty+1 == len(used) {
			return 1
		} else {
			return 0
		}
	}

	_, is_used := used[current]
	if is_used {
		return 0
	}

	used[current] = true
	result := 0

	neighbours := []Cell{
		{current.i - 1, current.j},
		{current.i + 1, current.j},
		{current.i, current.j - 1},
		{current.i, current.j + 1},
	}

	for _, new_current := range neighbours {
		if 0 <= new_current.i && new_current.i < len(grid) && 0 <= new_current.j && new_current.j < len(grid[0]) {
			result += internalUiquePaths(grid, empty, new_current, used)
		}
	}

	delete(used, current)
	return result
}
