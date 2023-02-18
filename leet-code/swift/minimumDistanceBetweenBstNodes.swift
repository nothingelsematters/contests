func minDiffInBST(_ root: TreeNode?) -> Int {
    return innerMinDiffInBST(root).2
}

func innerMinDiffInBST(_ root: TreeNode?) -> (Int, Int, Int) {
    guard let root = root else { return (1000000, -1000000, 1000000) }

    let (leftMin, leftMax, leftDiff) = innerMinDiffInBST(root.left)
    let (rightMin, rightMax, rightDiff) = innerMinDiffInBST(root.right)

    return (
        min(root.val, leftMin),
        max(root.val, rightMax),
        min(
            min(root.val - leftMax, rightMin - root.val),
            min(leftDiff, rightDiff)
        )
    )
}
