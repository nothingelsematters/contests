# [Code Wars](https://www.codewars.com/) Tasks

![badge](https://www.codewars.com/users/nothingelsematters/badges/micro)

- [6kyu. Camel Case](#6kyu-camel-case)
- [6kyu. Roman Numerals Encoder](#6kyu-roman-numerals-encoder)
- [4kyu. Range Extraction](#4kyu-range-extraction)
- [3kyu. Tricky Kotlin #8: Simple for-comprehension](#3kyu-tricky-kotlin-8-simple-for-comprehension)

## [6kyu. Camel Case](https://www.codewars.com/kata/517abf86da9663f1d2000003)

> [*Solution*](CamelCase.kt)

Complete the method/function so that it converts dash/underscore delimited words into camel casing. The first word within the output should be capitalized only if the original word was capitalized (known as Upper Camel Case, also often referred to as Pascal case).

Examples `"the-stealth-warrior"` gets converted to `"theStealthWarrior"`,
`"The_Stealth_Warrior"` gets converted to `"TheStealthWarrior"`

## [6kyu. Roman Numerals Encoder](https://www.codewars.com/kata/51b62bf6a9c58071c600001b)

> [*Solution*](RomanNumerals.kt)

Create a function taking a positive integer as its parameter and returning a string containing the Roman Numeral representation of that integer.

Modern Roman numerals are written by expressing each digit separately starting with the left most digit and skipping any digit with a value of zero. In Roman numerals 1990 is rendered: 1000=M, 900=CM, 90=XC; resulting in MCMXC. 2008 is written as 2000=MM, 8=VIII; or MMVIII. 1666 uses each Roman symbol in descending order: MDCLXVI.

Example:

```kotlin
encode(1000) // should return "M"
```

Help:

```
Symbol    Value
I          1
V          5
X          10
L          50
C          100
D          500
M          1,000
```

Remember that there can't be more than 3 identical symbols in a row.

More about roman numerals - http://en.wikipedia.org/wiki/Roman_numerals

## [4kyu. Range Extraction](https://www.codewars.com/kata/51ba717bb08c1cd60f00002f)

> [*Solution*](RangeExtraction.kt)

A format for expressing an ordered list of integers is to use a comma separated list of either

- individual integers
- or a range of integers denoted by the starting integer separated from the end integer in the range by a dash, '-'. The range includes all integers in the interval including both endpoints. It is not considered a range unless it spans at least 3 numbers. For example "12,13,15-17"

Complete the solution so that it takes a list of integers in increasing order and returns a correctly formatted string in the range format.

Example:

```
solution([-6, -3, -2, -1, 0, 1, 3, 4, 5, 7, 8, 9, 10, 11, 14, 15, 17, 18, 19, 20]);
// returns "-6,-3-1,3-5,7-11,14,15,17-20"
```

## [3kyu. Tricky Kotlin #8: Simple for-comprehension](https://www.codewars.com/kata/5a6f71185084d76d2000001b)

> [*Solution*](ForComprehension.kt)

Let's build a simple for-comprehension DSL in kotlin!

Look, I have a function like this:

```kotlin
import java.util.Optional

fun plus(o1: Optional<Int>, o2: Optional<Int>): Optional<Int> {
    if (o1.isPresent && o2.isPresent)
        return Optional.of(o1.get() + o2.get())
    else
        return Optional.empty()
}
```

I can rewrite it with a more functional style:

```kotlin
fun plus(o1: Optional<Int>, o2: Optional<Int>): Optional<Int> =
    o1.flatMap { i1 ->
        o2.flatMap { i2 ->
            Optional.of(i1 + i2)
        }
    }
```

`o1.flatMap {...}` means if `o1` is not empty, invoke the closure with the value wrapped in `o1` and then use the result of the closure as the return value of `flatMap`.
Otherwise (if `o1` is empty) discard the closure and simply return `Optional.empty()`. As you can see, this code does the same thing as the original code.

In Scala, we can do it in a more elegant way called `for-comprehension`.

```scala
// this is scala, not kotlin
def plus(o1: Option[Int], o2: Option[Int]) = {
    for {
        i1 <- o1
        i2 <- o2
    } yield i1 + i2
}
// it's equivalent to
// o1.flatMap(i1 => o2.flatMap(i2 => Some(i1 + i2)))
```

But Kotlin has no similar feature to achieve this!

So in this kata, your task is to build a simple for-comprehension DSL in Kotlin, enable you to write the following code (like Scala):

```kotlin
fun plus(o1: Optional<Int>, o2: Optional<Int>): Optional<Int> =
    `for` {
        val i1: Int = bind(o1)
        val i2: Int = bind(o2)
        yield(i1 + i2) 
    }
// it's required to be equivalent to
// o1.flatMap { i1 -> o2.flatMap { i2 -> Optional.of(i1 + i2) } }
```

Don't worry, this DSL is only used for `java.util.Optional`, you don't need to deal with other types. Most of the test cases have already been provided for you. 
