private data class Condition(
    val variable: String,
    val greater: Boolean,
    val value: Int,
    val then: String,
)

private data class Rule(val conditions: List<Condition>, val fallback: String)

private fun readRulesAndRatings(): Pair<Map<String, Rule>, List<Map<String, Int>>> {
    val (ruleBlock, ratingBlock) = blocks()

    val rules = ruleBlock.asSequence()
        .map { line ->
            val name = line.substringBefore('{')

            val conditionList = line.substringAfter('{').substringBefore('}').split(',')
            val conditions = conditionList.subList(0, conditionList.size - 1).map {
                val (variable, valueString, then) = it.split('<', '>', ':')
                val value = valueString.toInt()
                Condition(variable, it[variable.length] == '>', value, then)
            }

            name to Rule(conditions, conditionList.last())
        }
        .toMap()

    val ratings = ratingBlock.map { line ->
        line.substring(1, line.length - 1)
            .splitToSequence(',')
            .map {
                val (variable, valueString) = it.split('=')
                variable to valueString.toInt()
            }
            .toMap()
    }

    return rules to ratings
}

private fun part1(rules: Map<String, Rule>, ratings: List<Map<String, Int>>): Int =
    ratings.asSequence()
        .filter { rating ->
            var current = "in"

            while (true) {
                val rule = rules[current] ?: break
                current = rule.conditions
                    .find {
                        val value = rating[it.variable]!!
                        it.greater && value > it.value || !it.greater && value < it.value
                    }
                    ?.then
                    ?: rule.fallback
            }

            current == "A"
        }
        .sumOf { rating -> rating.values.sum() }

private fun findPossibleRanges(
    rules: Map<String, Rule>,
    ruleName: String,
    initialRanges: Map<String, Interval>,
): List<Map<String, Interval>> {
    when {
        ruleName == "A" -> return listOf(initialRanges.toMap())
        ruleName == "R" || initialRanges.values.any { it.isEmpty() } -> return emptyList()
    }

    val rule = rules[ruleName]!!

    val possibleRanges = mutableListOf<Map<String, Interval>>()
    val currentRanges = initialRanges.toMutableMap()

    for (condition in rule.conditions) {
        val r = currentRanges[condition.variable]!!

        val trueConditionRanges = currentRanges.toMutableMap()
        trueConditionRanges[condition.variable] = when {
            condition.greater && r.end <= condition.value ||
                !condition.greater && r.start >= condition.value -> Interval.EMPTY

            condition.greater -> Interval(maxOf(r.start, condition.value + 1L), r.end)
            else -> Interval(r.start, minOf(r.end, condition.value - 1L))
        }

        possibleRanges += findPossibleRanges(rules, condition.then, trueConditionRanges)

        currentRanges[condition.variable] = when {
            !condition.greater && r.end <= condition.value ||
                condition.greater && r.start >= condition.value -> Interval.EMPTY

            condition.greater -> Interval(r.start, minOf(r.end, condition.value.toLong()))
            else -> Interval(maxOf(r.start, condition.value.toLong()), r.end)
        }
    }

    possibleRanges += findPossibleRanges(rules, rule.fallback, currentRanges)

    return possibleRanges
}

private fun part2(rules: Map<String, Rule>): Long {
    val ranges = "xmas".asSequence().map { it.toString() to Interval(1, 4000) }.toMap()
    return findPossibleRanges(rules, "in", ranges)
        .sumOf { map -> map.values.multiplicationOf { it.size } }
}

fun main() {
    val (rules, ratings) = readRulesAndRatings()

    val first = part1(rules, ratings)
    val second = part2(rules)

    println("$first $second")
}
