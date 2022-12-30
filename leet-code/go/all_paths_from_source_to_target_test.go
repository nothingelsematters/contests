package leetcode

import (
	"reflect"
	"testing"
)

func TestAllPathsSourceTargetSample1(t *testing.T) {
	graph := [][]int{{1, 2}, {3}, {3}, {}}
	result := allPathsSourceTarget(graph)

	if !reflect.DeepEqual(result, [][]int{{0, 1, 3}, {0, 2, 3}}) {
		t.Error(result)
	}
}
