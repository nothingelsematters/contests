package leetcode

func canCompleteCircuit(gas []int, cost []int) int {
	start := len(gas) - 1
	current := gas[start] - cost[start]

	for i := 0; i != start; i++ {
		if current < 0 {
			for i != start && current < 0 {
				start--
				current += gas[start] - cost[start]
			}

			if i == start || current < 0 {
				break
			}
		}

		current += gas[i] - cost[i]
	}

	if current < 0 {
		return -1
	} else {
		return start
	}
}
