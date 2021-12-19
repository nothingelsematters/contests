import java.io.InputStream

private sealed class SnailFishNumber {

    abstract var parent: SnailFishPair?

    abstract fun magnitude(): Int

    abstract fun deepCopy(): SnailFishNumber

    operator fun plus(other: SnailFishNumber): SnailFishNumber {
        val result = SnailFishPair(this, other)
        result.reduce()
        return result
    }

    protected tailrec fun reduce() {
        if (explode() || split()) reduce()
    }

    private fun explode(): Boolean {
        val found = findFourth() ?: return false
        found.findLeftNumber()?.run { number += (found.left as SnailFishLiteral).number }
        found.findRightNumber()?.run { number += (found.right as SnailFishLiteral).number }

        val foundParent = found.parent!!
        val new = SnailFishLiteral(0, foundParent)
        new.parent = foundParent

        if (foundParent.left === found) {
            foundParent.left = new
        } else {
            foundParent.right = new
        }

        return true
    }

    fun split(): Boolean {
        val found = findLeftGreater10() ?: return false
        val foundParent = found.parent!!

        val new = SnailFishPair(
            SnailFishLiteral(found.number / 2),
            SnailFishLiteral(found.number / 2 + (found.number and 1))
        )
        new.parent = foundParent

        if (foundParent.left === found) {
            foundParent.left = new
        } else {
            foundParent.right = new
        }

        return true
    }

    private fun findFourth(depth: Int = 0): SnailFishPair? {
        if (depth == 4) return this as? SnailFishPair

        return when (val found = this) {
            is SnailFishPair -> found.left.findFourth(depth + 1) ?: found.right.findFourth(depth + 1)
            is SnailFishLiteral -> null
        }
    }

    protected fun findLeftNumber(): SnailFishLiteral? {
        val currentParent = parent ?: return null
        return if (currentParent.right === this) currentParent.left.diveRight() else currentParent.findLeftNumber()
    }

    protected fun findRightNumber(): SnailFishLiteral? {
        val currentParent = parent ?: return null
        return if (currentParent.left === this) currentParent.right.diveLeft() else currentParent.findRightNumber()
    }

    private fun findLeftGreater10(): SnailFishLiteral? = when (this) {
        is SnailFishLiteral -> if (number > 9) this else null
        is SnailFishPair -> left.findLeftGreater10() ?: right.findLeftGreater10()
    }

    protected abstract fun diveLeft(): SnailFishLiteral

    protected abstract fun diveRight(): SnailFishLiteral

    companion object {
        fun parse(inputStream: InputStream): SnailFishNumber? = when (val read = inputStream.read()) {
            -1 -> null
            '['.code -> {
                val left = parse(inputStream).expect()
                check(inputStream.read() == ','.code)
                val right = parse(inputStream).expect()
                check(inputStream.read() == ']'.code)
                SnailFishPair(left, right)
            }
            in '0'.code..'9'.code -> SnailFishLiteral(read.toChar().toString().toInt())
            else -> error("Invalid number")
        }

        fun parse(string: String) = parse(string.byteInputStream())
    }
}

private data class SnailFishPair(
    var left: SnailFishNumber,
    var right: SnailFishNumber,
    override var parent: SnailFishPair? = null,
) : SnailFishNumber() {

    init {
        left.parent = this
        right.parent = this
    }

    override fun magnitude() = 3 * left.magnitude() + 2 * right.magnitude()

    override fun deepCopy() = SnailFishPair(left.deepCopy(), right.deepCopy())

    override fun toString() = "[$left,$right]"

    override fun diveLeft(): SnailFishLiteral = when (val left = left) {
        is SnailFishLiteral -> left
        is SnailFishPair -> left.diveLeft()
    }

    override fun diveRight(): SnailFishLiteral = when (val right = right) {
        is SnailFishLiteral -> right
        is SnailFishPair -> right.diveRight()
    }
}

private data class SnailFishLiteral(var number: Int, override var parent: SnailFishPair? = null) : SnailFishNumber() {

    override fun magnitude() = number

    override fun deepCopy() = SnailFishLiteral(number)

    override fun toString() = "$number"

    override fun diveLeft() = this

    override fun diveRight() = this
}

private fun part1(numbers: List<SnailFishNumber>) =
    numbers
        .asSequence()
        .map { it.deepCopy() }
        .reduce(SnailFishNumber::plus)
        .magnitude()

private fun part2(numbers: List<SnailFishNumber>) =
    (numbers cartesian numbers)
        .filter { (a, b) -> a !== b }
        .maxOfOrNull { (a, b) -> (a.deepCopy() + b.deepCopy()).magnitude() }

fun main() {
    val numbers = mapLines { SnailFishNumber.parse(it).expect() }
    println(part1(numbers))
    println(part2(numbers))
}
