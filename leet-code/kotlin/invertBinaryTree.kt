class TreeNode(var `val`: Int) {
     var left: TreeNode? = null
     var right: TreeNode? = null
}

val TreeNode.value
    get() = `val`

fun invertTree(root: TreeNode?): TreeNode? {
    val newRoot = root?.value?.let { TreeNode(it) }
    var qNew = listOf(newRoot)
    var qOld = listOf(root)

    while (qOld.isNotEmpty()) {
        val newQOld = mutableListOf<TreeNode?>()
        val newQNew = mutableListOf<TreeNode?>()

        qOld.asReversed()
            .asSequence()
            .map { it?.right to it?.left }
            .zip(qNew.asSequence())
            .mapNotNull { (a, b) -> b?.let { a to b } }
            .forEach { (old, new) ->
                val (r, l) = old
                new.right = l?.let { TreeNode(it.value) }
                new.left = r?.let { TreeNode(it.value) }

                newQOld += r
                newQOld += l
                newQNew += new.left
                newQNew += new.right
            }

        qNew = newQNew
        qOld = newQOld.asReversed()
    }

    return newRoot
}
