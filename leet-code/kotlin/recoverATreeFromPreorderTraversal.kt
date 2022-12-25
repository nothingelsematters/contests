/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun recoverFromPreorder(traversal: String): TreeNode? {
        val parents = mutableMapOf<TreeNode, TreeNode>()

        val (root, newIndex) = readNumber(traversal, 0)
        var index = newIndex
        var currentNode = root
        var currentDepth = 0


        while (index < traversal.length) {
            val depth = readDepth(traversal, index)
            index += depth

            val (node, length) = readNumber(traversal, index)
            index += length

            while (currentDepth >= depth) {
                currentNode = parents[currentNode]!!
                currentDepth--
            }

            parents[node] = currentNode
            if (currentNode.left == null) currentNode.left = node else currentNode.right = node
            currentNode = node
            currentDepth = depth
        }

        return root
    }

    private fun readNumber(traversal: String, index: Int): Pair<TreeNode, Int> {
        val number = traversal.substring(index).asSequence().takeWhile { it.isDigit() }.joinToString("")
        return TreeNode(number.toInt()) to number.length
    }

    private fun readDepth(traversal: String, index: Int): Int =
        traversal.substring(index).asSequence().takeWhile { it == '-' }.count()
}
