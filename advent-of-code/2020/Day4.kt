fun isValid(passport: Map<String, String>): Boolean {

    operator fun IntRange.contains(string: String) = string.toIntOrNull() in this

    fun isValid(name: String, function: String.() -> Boolean) = passport[name]?.run(function) == true

    fun isValid(name: String, range: IntRange) = isValid(name) { this in range }

    fun isValid(name: String, regex: String) = isValid(name) { matches(regex.toRegex()) }

    return isValid("byr", 1920..2002) &&
        isValid("iyr", 2010..2020) &&
        isValid("eyr", 2020..2030) &&
        isValid("hgt") { endsWith("cm") && dropLast(2) in 150..193 || endsWith("in") && dropLast(2) in 59..76 } &&
        isValid("hcl", "#[\\da-f]{6}") &&
        isValid("ecl", "(amb|blu|brn|gry|grn|hzl|oth)") &&
        isValid("pid", "\\d{9}")
}

fun countValid(passports: List<Map<String, String>>, validator: (Map<String, String>) -> Boolean) =
    passports.asSequence().filter(validator).count()

fun main() {
    val ids = System.`in`.bufferedReader()
        .readText()
        .split("\n\n")
        .asSequence()
        .map {
            it.split(' ', '\n')
                .asSequence()
                .filter { it.isNotEmpty() }
                .map {
                    val (key, value) = it.split(':')
                    key to value
                }
                .toMap()
        }
        .toList()

    val first = countValid(ids) { map -> listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid").all { it in map } }
    val second = countValid(ids, ::isValid)
    println("$first $second")
}
