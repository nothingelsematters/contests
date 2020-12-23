sealed class ListNode<T>

data class DataNode<T>(val value: T, var previous: ListNode<T>, var next: ListNode<T>) : ListNode<T>() {

    override fun toString() = "Data($value)"
}

class Tail<T>(var previous: ListNode<T>) : ListNode<T>()

class Head<T>(var next: ListNode<T>? = null) : ListNode<T>()

class IntrusiveList<T> {

    private val nodes = mutableMapOf<T, DataNode<T>>()

    private var tail: Tail<T>

    private val head = Head<T>(null)

    init {
        tail = Tail(head)
        head.next = tail
    }

    fun pollFirst(): T {
        val node = head.next as DataNode<T>
        val next = node.next
        head.next = next

        when (next) {
            is Tail<T> -> next.previous = head
            is DataNode<T> -> next.previous = head
            else -> throw IllegalStateException()
        }

        nodes.remove(node.value)
        return node.value
    }

    fun addLast(value: T) {
        val node = DataNode(value, tail.previous, tail)
        nodes[value] = node

        when (val prev = tail.previous) {
            is Head<T> -> prev.next = node
            is DataNode<T> -> prev.next = node
            else -> throw IllegalStateException()
        }

        tail.previous = node
        nodes[value] = node
    }

    fun addAfter(after: T, value: T) {
        val afterNode = nodes[after].expect()
        val next = afterNode.next
        val node = DataNode(value, afterNode, next)
        nodes[value] = node

        when (next) {
            is Tail<T> -> next.previous = node
            is DataNode<T> -> next.previous = node
            else -> throw IllegalStateException()
        }

        afterNode.next = node
    }

    fun toList(): List<T> {
        var current: ListNode<T> = head.next.expect()
        val list = mutableListOf<T>()

        while (current != tail) {
            val node = current as DataNode<T>
            list.add(node.value)
            current = node.next
        }

        return list
    }
}

fun spin(initials: List<Int>, iterations: Int): List<Int> {
    val intrusiveList = IntrusiveList<Int>()

    initials.forEach { intrusiveList.addLast(it) }

    repeat(iterations) {
        val start = intrusiveList.pollFirst()
        val removed = List(3) { intrusiveList.pollFirst() }

        var destination = (1..removed.size)
            .indexOfFirst { start - it !in removed }
            .let { if (it == -1) removed.size else it }
            .let { start - it - 1 }

        if (destination == 0) {
            destination = removed.indices
                .indexOfFirst { initials.size - it !in removed }
                .let { if (it == -1) removed.size else it }
                .let { initials.size - it }
        }

        removed.reversed().forEach { intrusiveList.addAfter(destination, it) }
        intrusiveList.addLast(start)
    }
    return intrusiveList.toList()
}

fun main() {
    val initials = readLine().expect().map { it.toString().toIntOrNull().expect() }

    val values = spin(initials, 100)
    val index = values.indexOf(1)
    val first = (values.drop(index + 1) + values.take(index)).joinToString("")

    val highest = initials.maxOrNull().expect()
    val millionValues = spin(initials + List(1000000 - initials.size) { it + highest + 1 }, 10000000)
    val millionIndex = millionValues.indexOf(1)
    val second = (1..2).asSequence()
        .map { millionValues[(millionIndex + it) % millionValues.size] }
        .map { it.toLong() }
        .reduce(Long::times)

    println("$first $second")
}
