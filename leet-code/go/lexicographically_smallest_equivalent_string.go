package leetcode

func smallestEquivalentString(s1 string, s2 string, baseStr string) string {
	parents, ranks := unionInitialize()

	for i := 0; i < len(s1); i++ {
		unionUnion(parents, ranks, int(s1[i]-'a'), int(s2[i]-'a'))
	}

	minimumGroupInt := make(map[int]int)
	for i := range parents {
		parent := unionFind(parents, i)
		min, ok := minimumGroupInt[parent]
		if !ok || min > i {
			minimumGroupInt[parent] = i
		}
	}

	byteMap := make(map[byte]byte)
	for i := range parents {
		byteMap[byte(i)+'a'] = byte(minimumGroupInt[unionFind(parents, i)]) + 'a'
	}

	result := []byte{}
	for _, byte := range []byte(baseStr) {
		result = append(result, byteMap[byte])
	}
	return string(result)
}

func unionInitialize() ([]int, []int) {
	parents := []int{}

	for i := 'a'; i <= 'z'; i++ {
		parents = append(parents, len(parents))
	}

	ranks := make([]int, int('z'-'a')+1)
	return parents, ranks
}

func unionFind(parents []int, i int) int {
	if parents[i] != i {
		parents[i] = unionFind(parents, parents[i])
	}
	return parents[i]
}

func unionUnion(parents []int, ranks []int, i int, j int) {
	iParent := unionFind(parents, i)
	jParent := unionFind(parents, j)

	if iParent == jParent {
		return
	}

	if ranks[iParent] == ranks[jParent] {
		ranks[iParent]++
	}

	if ranks[iParent] < ranks[jParent] {
		parents[iParent] = jParent
	} else {
		parents[jParent] = iParent
	}
}
