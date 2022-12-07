private sealed class FileTree {
    abstract val name: String
    abstract val size: Int
}

private data class File(override val name: String, override val size: Int): FileTree()

private data class Directory(
    override val name: String,
    val parent: Directory?,
    val fileTrees: MutableMap<String, FileTree> = mutableMapOf(),
) : FileTree() {
    override val size: Int
        get() = fileTrees.values.sumOf { it.size }

    fun add(fileTree: FileTree) {
        fileTrees[fileTree.name] = fileTree
    }

    fun asSequence(): Sequence<FileTree> =
        fileTrees
            .values
            .asSequence()
            .flatMap {
                when (it) {
                    is Directory -> it.asSequence() + it
                    is File -> sequenceOf(it)
                }
            }
}

private fun getFileTree(): Directory {
    val input = getFullInput().trimEnd()
    val root = Directory("/", null)

    input
        .splitToSequence("\n$ ")
        .drop(1)
        .map {
            val splittedLaunch = it.split('\n')
            splittedLaunch.firstOrNull().unwrap() to splittedLaunch.drop(1)
        }
        .fold(root) { currentDirectory, (command, output) ->
            when {
                command.startsWith("cd") -> {
                    when (val name = command.split(' ', limit = 2).getOrNull(1).unwrap()) {
                        ".." -> currentDirectory.parent.unwrap()
                        else -> Directory(name, currentDirectory).also(currentDirectory::add)
                    }
                }
                command.startsWith("ls") -> {
                    output.asSequence()
                        .filter { !it.startsWith("dir") }
                        .map {
                            val (sizeString, fileName) = it.split(' ', limit = 2)
                            val size = sizeString.toIntOrNull().unwrap()
                            File(fileName, size)
                        }
                        .forEach { currentDirectory.add(it) }

                    currentDirectory
                }
                else -> expect()
            }
        }

    return root
}

fun main() {
    val root = getFileTree()

    val first = root.asSequence().filter { it is Directory }.map { it.size }.filter { it <= 100_000 }.sum()

    val second = root.asSequence()
        .filter { it is Directory }
        .map { it.size }
        .filter { it + 70_000_000 - root.size > 30_000_000 }
        .min()

    println("$first $second")
}
