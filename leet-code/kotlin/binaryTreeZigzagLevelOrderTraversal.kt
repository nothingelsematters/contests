private val TreeNode.value
        get() = `val`

fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
    val levels = mutableListOf<List<Int>>()
    var q = listOf(root ?: return emptyList())
    var flag = false

    do {
        levels += (if (flag) q.asReversed() else q).map { it.value }
        flag = !flag
        q = q.asSequence().flatMap { sequenceOf(it.left, it.right) }.filterNotNull().toList()
    } while (q.isNotEmpty())

    return levels
}
