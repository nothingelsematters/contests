import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.reduce

private operator fun <T> List<T>.component6(): T = this[5]

private typealias Blueprint = Array<IntArray>

private data class MiningState(val robots: IntArray, var resources: IntArray) {

    fun deepCopy() = MiningState(robots.clone(), resources.clone())

    private fun inNeed(blueprint: Blueprint, robotIndex: Int): Boolean = when (robotIndex) {
        3 -> true
        2 -> robots[2] < blueprint[3][2]
        1 -> inNeed(blueprint, 2) && robots[2] < blueprint[2][1]
        0 -> when {
            robots[0] < blueprint[3][0] -> true
            robots[2] >= blueprint[3][2] -> false
            robots[0] < blueprint[2][0] -> true
            robots[1] >= blueprint[2][1] -> false
            robots[0] < blueprint[1][0] -> true
            else -> false
        }
        else -> error("Unexpected robot index")
    }

    fun reserveRobot(blueprint: Blueprint, index: Int): MiningState? {
        val purchase = blueprint[index]

        if (!inNeed(blueprint, index)) {
            return null
        }

        if (!resources.asSequence().zip(blueprint[index].asSequence()).all { (a, b) -> a >= b }) {
            return null
        }

        val miningStateCopy = deepCopy()
        purchase.forEachIndexed { i, cost -> miningStateCopy.resources[i] -= cost }
        return miningStateCopy
    }

    fun mine() {
        robots.forEachIndexed { index, robotNumber -> resources[index] += robotNumber }
    }

    fun craftRobot(index: Int) {
        robots[index]++
    }

    override fun equals(other: Any?): Boolean = when {
        this === other -> true
        other !is MiningState -> false
        !robots.contentEquals(other.robots) -> false
        !resources.contentEquals(other.resources) -> false
        else -> true
    }

    override fun hashCode() = 31 * robots.contentHashCode() + resources.contentHashCode()

    companion object {
        private const val SIZE = 4

        val INITIAL = MiningState(intArrayOf(1, 0, 0, 0), IntArray(SIZE))
    }
}

private fun mine(
    blueprint: Blueprint,
    miningState: MiningState,
    minutes: Int,
    memoization: MutableMap<Pair<MiningState, Int>, Int> = mutableMapOf(),
): Int {
    val memoized = memoization[miningState to minutes]
    when {
        minutes == 0 -> return miningState.resources.last()
        memoized != null -> return memoized
    }

    val best = blueprint.indices
        .asSequence()
        .mapNotNull { index -> miningState.reserveRobot(blueprint, index)?.let { index to it  } }
        .plus(null to miningState)
        .onEach {  (index, newMiningState) ->
            newMiningState.mine()
            index?.let { newMiningState.craftRobot(it) }
        }
        .maxOf { mine(blueprint, it.second, minutes - 1, memoization) }

    if (minutes >= 10) {
        memoization[miningState to minutes] = best
    }
    return best
}

suspend fun main() {
    val blueprints = mapLines {  line ->
        val (oreOre, clayOre, obsidianOre, obsidianClay, geodeOre, geodeObsidian) =
            ("""Blueprint \d+: Each ore robot costs (\d)+ ore. """ +
                """Each clay robot costs (\d+) ore. """ +
                """Each obsidian robot costs (\d+) ore and (\d+) clay. """ +
                """Each geode robot costs (\d+) ore and (\d+) obsidian.""")
                .toRegex()
                .matchEntire(line)!!
                .destructured
                .toList()
                .map { it.toInt() }

        arrayOf(
            intArrayOf(oreOre, 0, 0),
            intArrayOf(clayOre, 0, 0),
            intArrayOf(obsidianOre, obsidianClay, 0),
            intArrayOf(geodeOre, 0, geodeObsidian),
        )
    }


    val first = coroutineScope {
        blueprints
            .mapIndexed { i, it -> async { (i + 1) * mine(it, MiningState.INITIAL.deepCopy(), 24) } }
            .sumOf { it.await() }
    }

    val second = coroutineScope {
        blueprints[0..2]
            .map { async { mine(it, MiningState.INITIAL.deepCopy(), 32) } }
            .asFlow()
            .map { it.await() }
            .reduce(Int::times)
    }

    println(first)
    println(second)
}
