func maxDepth(_ root: TreeNode?) -> Int {
    if let realRoot = root {
        return max(maxDepth(realRoot.left), maxDepth(realRoot.right)) + 1
    }
    return 0
}
