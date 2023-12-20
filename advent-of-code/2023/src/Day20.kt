private sealed class Module {
    abstract val name: String
    abstract val outputs: List<String>

    abstract fun process(pulse: Pulse): List<Pulse>

    protected fun sendSignal(highSignal: Boolean): List<Pulse> =
        outputs.map { Pulse(name, it, highSignal) }
}

private data class Broadcaster(
    override val name: String,
    override val outputs: List<String>,
) : Module() {

    override fun process(pulse: Pulse): List<Pulse> = sendSignal(pulse.highSignal)
}

private data class FlipFlop(
    override val name: String,
    override val outputs: List<String>,
    var isOn: Boolean = false,
) : Module() {

    override fun process(pulse: Pulse): List<Pulse> {
        if (pulse.highSignal) return emptyList()
        isOn = !isOn
        return sendSignal(isOn)
    }
}

private class Conjunction(
    override val name: String,
    override val outputs: List<String>,
    inputs: List<String>,
) : Module() {

    private val inputMap = inputs.asSequence().map { it to false }.toMap().toMutableMap()

    override fun process(pulse: Pulse): List<Pulse> {
        inputMap[pulse.from] = pulse.highSignal
        return sendSignal(!inputMap.values.all { it })
    }
}

private data class Pulse(val from: String, val to: String, val highSignal: Boolean)

private fun makeModules(lines: List<Pair<String, List<String>>>): Map<String, Module> =
    lines.asSequence()
        .map { (nameString, outputs) ->
            val name = nameString.trimStart('&', '%')

            when (nameString.first()) {
                '%' -> FlipFlop(name, outputs)
                '&' -> {
                    val inputs = lines.asSequence()
                        .filter { name in it.second }
                        .map { it.first.trimStart('&', '%') }
                        .toList()
                    Conjunction(name, outputs, inputs)
                }

                else -> Broadcaster(name, outputs)
            }
        }
        .map { it.name to it }
        .toMap()

private fun pressButton(
    modules: Map<String, Module>,
    until: () -> Boolean,
    interact: (Pulse) -> Unit,
) {
    while (until()) {
        val q = ArrayDeque<Pulse>()
        q += Pulse("button", "broadcaster", false)

        while (q.isNotEmpty()) {
            val pulse = q.removeFirst()
            interact(pulse)
            q += modules[pulse.to]?.process(pulse).orEmpty()
        }
    }
}

private fun part1(modules: Map<String, Module>): Long {
    var lowPulses = 0
    var highPulses = 0
    var i = 0

    pressButton(modules, { i++ < 1_000 }) {
        if (it.highSignal) highPulses++ else lowPulses++
    }

    return lowPulses.toLong() * highPulses
}

private fun part2(modules: Map<String, Module>): Long {
    val prev = modules.values.single { "rx" in it.outputs }

    val prevPrev = modules.values.asSequence()
        .filter { prev.name in it.outputs }
        .map { it.name }
        .toList()

    var i = 0
    val cycles = mutableMapOf<String, Int>()

    pressButton(
        modules,
        {
            i++
            !prevPrev.all { it in cycles }
        },
    ) {
        if (it.highSignal && it.from in prevPrev && it.from !in cycles) {
            cycles[it.from] = i
        }
    }

    return cycles.values.toList().lowestCommonMultiplier()
}

fun main() {
    val lines = mapLines { line ->
        val (name, outputString) = line.split(" -> ")
        name to outputString.split(", ")
    }

    val first = part1(makeModules(lines))
    val second = part2(makeModules(lines))

    println("$first $second")
}
