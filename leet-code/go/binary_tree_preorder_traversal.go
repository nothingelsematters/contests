package leetcode

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func preorderTraversal(root *TreeNode) []int {
	result := []int{}

	if root != nil {
		result = append(result, root.Val)
		result = append(result, preorderTraversal(root.Left)...)
		result = append(result, preorderTraversal(root.Right)...)
	}

	return result
}
