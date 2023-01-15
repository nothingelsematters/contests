package leetcode

import "sort"

func numberOfGoodPaths(vals []int, edges [][]int) int {
	children := make([][]int, len(vals))
	for _, edge := range edges {
		if vals[edge[0]] >= vals[edge[1]] {
			children[edge[0]] = append(children[edge[0]], edge[1])
		} else {
			children[edge[1]] = append(children[edge[1]], edge[0])
		}
	}

	valToIndex := make(map[int][]int)
	for i, val := range vals {
		_, ok := valToIndex[val]
		if ok {
			valToIndex[val] = append(valToIndex[val], i)
		} else {
			valToIndex[val] = []int{i}
		}
	}

	valKeys := []int{}
	for k := range valToIndex {
		valKeys = append(valKeys, k)
	}
	sort.Ints(valKeys)

	unions := nogdUnionInitialize(len(vals))
	result := len(vals)

	for _, valKey := range valKeys {
		indices := valToIndex[valKey]

		for _, i := range indices {
			for _, child := range children[i] {
				nogdUnionUnion(unions, i, child)
			}
		}

		group := make(map[int]int)
		for _, i := range indices {
			key := nogdUnionFind(unions, i)
			_, ok := group[key]
			if ok {
				group[key]++
			} else {
				group[key] = 1
			}
		}

		for _, v := range group {
			result += v * (v - 1) / 2
		}
	}

	return result
}

func nogdUnionInitialize(size int) []int {
	parents := []int{}

	for i := 0; i < size; i++ {
		parents = append(parents, len(parents))
	}

	return parents
}

func nogdUnionFind(parents []int, i int) int {
	j := i
	for parents[j] != j {
		j = parents[j]
	}
	parents[i] = j
	return j
}

func nogdUnionUnion(parents []int, i int, j int) {
	iParent := nogdUnionFind(parents, i)
	jParent := nogdUnionFind(parents, j)
	parents[iParent] = jParent
}
