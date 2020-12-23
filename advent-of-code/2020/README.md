# [Advent Of Code 2020](https://adventofcode.com/2020/)

- [Day 1: Report Repair](#day-1-report-repair)
  [![Kotlin](https://img.shields.io/badge/Kotlin-grey?style=flat-square&logo=Kotlin)](Day1.kt)
  [![Haskell](https://img.shields.io/badge/Haskell-grey?style=flat-square&logo=Haskell)](Day1.hs)

- [Day 2: Password Philosophy](#day-2-password-philosophy)
  [![Kotlin](https://img.shields.io/badge/Kotlin-grey?style=flat-square&logo=Kotlin)](Day2.kt)

- [Day 3: Toboggan Trajectory](#day-3-toboggan-trajectory)
  [![Kotlin](https://img.shields.io/badge/Kotlin-grey?style=flat-square&logo=Kotlin)](Day3.kt)

- [Day 4: Passport Processing](#day-4-passport-processing)
  [![Kotlin](https://img.shields.io/badge/Kotlin-grey?style=flat-square&logo=Kotlin)](Day4.kt)

- [Day 5: Binary Boarding](#day-5-binary-boarding)
  [![Kotlin](https://img.shields.io/badge/Kotlin-grey?style=flat-square&logo=Kotlin)](Day5.kt)

- [Day 6: Custom Customs](#day-6-custom-customs)
  [![Kotlin](https://img.shields.io/badge/Kotlin-grey?style=flat-square&logo=Kotlin)](Day6.kt)

- [Day 7: Handy Haversacks](#day-7-handy-haversacks)
  [![Kotlin](https://img.shields.io/badge/Kotlin-grey?style=flat-square&logo=Kotlin)](Day7.kt)

- [Day 8: Handheld Halting](#day-8-handheld-halting)
  [![Kotlin](https://img.shields.io/badge/Kotlin-grey?style=flat-square&logo=Kotlin)](Day8.kt)

- [Day 9: Encoding Error](#day-9-encoding-error)
  [![Kotlin](https://img.shields.io/badge/Kotlin-grey?style=flat-square&logo=Kotlin)](Day9.kt)
  [![Rust](https://img.shields.io/badge/Rust-grey?style=flat-square&logo=Rust)](Day9.rs)

- [Day 10: Adapter Array](#day-10-adapter-array)
  [![OCaml](https://img.shields.io/badge/OCaml-grey?style=flat-square&logo=OCaml)](Day10.ml)
  [![PostgreSQL](https://img.shields.io/badge/PostgreSQL-grey?style=flat-square&logo=PostgreSQL)](Day10.sql)

- [Day 11: Seating System](#day-11-seating-system)
  [![Kotlin](https://img.shields.io/badge/Kotlin-grey?style=flat-square&logo=Kotlin)](Day11.kt)
  [![Scala](https://img.shields.io/badge/Scala-grey?style=flat-square&logo=Scala)](Day11.scala)

- [Day 12: Rain Risk](#day-12-rain-risk)
  [![Kotlin](https://img.shields.io/badge/Kotlin-grey?style=flat-square&logo=Kotlin)](Day12.kt)
  [![Swift](https://img.shields.io/badge/Swift-grey?style=flat-square&logo=Swift)](Day12.swift)

- [Day 13: Shuttle Search](#day-13-shuttle-search)
  [![Kotlin](https://img.shields.io/badge/Kotlin-grey?style=flat-square&logo=Kotlin)](Day13.kt)

- [Day 14: Docking Data](#day-14-docking-data)
  [![Kotlin](https://img.shields.io/badge/Kotlin-grey?style=flat-square&logo=Kotlin)](Day14.kt)

- [Day 15: Rambunctious Recitation](#day-15-rambunctious-recitation)
  [![Kotlin](https://img.shields.io/badge/Kotlin-grey?style=flat-square&logo=Kotlin)](Day15.kt)
  [![Julia](https://img.shields.io/badge/Julia-grey?style=flat-square&logo=Julia)](Day15.jl)

- [Day 16: Ticket Translation](#day-16-ticket-translation)
  [![Kotlin](https://img.shields.io/badge/Kotlin-grey?style=flat-square&logo=Kotlin)](Day16.kt)

- [Day 17: Conway Cubes](#day-17-conway-cubes)
  [![Kotlin](https://img.shields.io/badge/Kotlin-grey?style=flat-square&logo=Kotlin)](Day17.kt)
  [![TypeScript](https://img.shields.io/badge/TypeScript-grey?style=flat-square&logo=TypeScript)](Day17.ts)

- [Day 18: Operation Order](#day-18-operation-order)
  [![Kotlin](https://img.shields.io/badge/Kotlin-grey?style=flat-square&logo=Kotlin)](Day18.kt)
  [![lalr-generator](https://img.shields.io/badge/nothingelsematters-lalr--generator-blue?style=flat-square&logo=GitHub)](https://github.com/nothingelsematters/lalr-generator)

- [Day 19: Monster Messages](#day-19-monster-messages)
  [![Kotlin](https://img.shields.io/badge/Kotlin-grey?style=flat-square&logo=Kotlin)](Day19.kt)
  [![Ruby](https://img.shields.io/badge/Ruby-grey?style=flat-square&logo=Ruby)](Day19.rb)

- [Day 20: Jurassic Jigsaw](#day-20-jurassic-jigsaw)
  [![Kotlin](https://img.shields.io/badge/Kotlin-grey?style=flat-square&logo=Kotlin)](Day20.kt)

- [Day 21: Allergen Assessment](#day-21-allergen-assessment)
  [![Kotlin](https://img.shields.io/badge/Kotlin-grey?style=flat-square&logo=Kotlin)](Day21.kt)

- [Day 22: Crab Combat](#day-22-crab-combat)
  [![Kotlin](https://img.shields.io/badge/Kotlin-grey?style=flat-square&logo=Kotlin)](Day22.kt)

- [Day 23: Crab Cups](#day-23-crab-cups)
  [![Kotlin](https://img.shields.io/badge/Kotlin-grey?style=flat-square&logo=Kotlin)](Day23.kt)


## [Day 1: Report Repair](https://adventofcode.com/2020/day/1)

After saving Christmas [five years in a row](https://adventofcode.com/2020/events), you've decided to take a vacation at a nice resort on a tropical island. Surely, Christmas will go on without you.

The tropical island has its own currency and is entirely cash-only.  The gold coins used there have a little picture of a starfish; the locals just call them **stars**. None of the currency exchanges seem to have heard of them, but somehow, you'll need to find fifty of these coins by the time you arrive so you can pay the deposit on your room.

To save your vacation, you need to get all **fifty stars** by December 25th.

Collect stars by solving puzzles.  Two puzzles will be made available on each day in the Advent calendar; the second puzzle is unlocked when you complete the first.  Each puzzle grants **one star**. Good luck!

Before you leave, the Elves in accounting just need you to fix your **expense report** (your puzzle input); apparently, something isn't quite adding up.

Specifically, they need you to **find the two entries that sum to `2020`** and then multiply those two numbers together.

For example, suppose your expense report contained the following:


```
1721
979
366
299
675
1456
```

In this list, the two entries that sum to `2020` are `1721` and `299`. Multiplying them together produces `1721 * 299 = 514579`, so the correct answer is `514579`.

Of course, your expense report is much larger. **Find the two entries that sum to `2020`; what do you get if you multiply them together?**


> Your puzzle answer was `776064`.


### Part Two

The Elves in accounting are thankful for your help; one of them even offers you a starfish coin they had left over from a past vacation. They offer you a second one if you can find **three** numbers in your expense report that meet the same criteria.

Using the above example again, the three entries that sum to `2020` are `979`, `366`, and `675`. Multiplying them together produces the answer, `241861950`.

In your expense report, **what is the product of the three entries that sum to `2020`?**


> Your puzzle answer was `6964490`.

## [Day 2: Password Philosophy](https://adventofcode.com/2020/day/2)

Your flight departs in a few days from the coastal airport; the easiest way down to the coast from here is via [toboggan](https://en.wikipedia.org/wiki/Toboggan).

The shopkeeper at the North Pole Toboggan Rental Shop is having a bad day. "Something's wrong with our computers; we can't log in!" You ask if you can take a look.

Their password database seems to be a little corrupted: some of the passwords wouldn't have been allowed by the Official Toboggan Corporate Policy that was in effect when they were chosen.

To try to debug the problem, they have created a list (your puzzle input) of **passwords** (according to the corrupted database) and **the corporate policy when that password was set**.

For example, suppose you have the following list:


```
1-3 a: abcde
1-3 b: cdefg
2-9 c: ccccccccc
```

Each line gives the password policy and then the password. The password policy indicates the lowest and highest number of times a given letter must appear for the password to be valid. For example, `1-3 a` means that the password must contain `a` at least `1` time and at most `3` times.

In the above example, `2` passwords are valid. The middle password, `cdefg`, is not; it contains no instances of `b`, but needs at least `1`. The first and third passwords are valid: they contain one `a` or nine `c`, both within the limits of their respective policies.

**How many passwords are valid** according to their policies?


> Your puzzle answer was `460`.


### Part Two

While it appears you validated the passwords correctly, they don't seem to be what the Official Toboggan Corporate Authentication System is expecting.

The shopkeeper suddenly realizes that he just accidentally explained the password policy rules from his old job at the sled rental place down the street! The Official Toboggan Corporate Policy actually works a little differently.

Each policy actually describes two **positions in the password**, where `1` means the first character, `2` means the second character, and so on. (Be careful; Toboggan Corporate Policies have no concept of "index zero"!) **Exactly one of these positions** must contain the given letter. Other occurrences of the letter are irrelevant for the purposes of policy enforcement.

Given the same example list from above:


+ `1-3 a: abcde` is **valid**: position `1` contains `a` and position `3` does not.
+ `1-3 b: cdefg` is **invalid**: neither position `1` nor position `3` contains `b`.
+ `2-9 c: ccccccccc` is **invalid**: both position `2` and position `9` contain `c`.

**How many passwords are valid** according to the new interpretation of the policies?**


> Your puzzle answer was `251`.

## [Day 3: Toboggan Trajectory](https://adventofcode.com/2020/day/3)

With the toboggan login problems resolved, you set off toward the airport. While travel by toboggan might be easy, it's certainly not safe: there's very minimal steering and the area is covered in trees. You'll need to see which angles will take you near the fewest trees.

Due to the local geology, trees in this area only grow on exact integer coordinates in a grid. You make a map (your puzzle input) of the open squares (`.`) and trees (`#`) you can see. For example:


```
..##.......
#...#...#..
.#....#..#.
..#.#...#.#
.#...##..#.
..#.##.....
.#.#.#....#
.#........#
#.##...#...
#...##....#
.#..#...#.#
```

These aren't the only trees, though; due to something you read about once involving arboreal genetics and biome stability, the same pattern repeats to the right many times:


```
..##.........##.........##.........##.........##.........##.......
#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..
.#....#..#..#....#..#..#....#..#..#....#..#..#....#..#..#....#..#.
..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#
.#...##..#..#...##..#..#...##..#..#...##..#..#...##..#..#...##..#.
..#.##.......#.##.......#.##.......#.##.......#.##.......#.##.....
.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#
.#........#.#........#.#........#.#........#.#........#.#........#
#.##...#...#.##...#...#.##...#...#.##...#...#.##...#...#.##...#...
#...##....##...##....##...##....##...##....##...##....##...##....#
.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#
```

You start on the open square (`.`) in the top-left corner and need to reach the bottom (below the bottom-most row on your map).

The toboggan can only follow a few specific slopes (you opted for a cheaper model that prefers rational numbers); start by **counting all the trees** you would encounter for the slope **right 3, down 1**:

From your starting position at the top-left, check the position that is right 3 and down 1. Then, check the position that is right 3 and down 1 from there, and so on until you go past the bottom of the map.

The locations you'd check in the above example are marked here with `O` where there was an open square and `X` where there was a tree:


```
..##.........##.........##.........##.........##.........##.......
#..O#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..
.#....X..#..#....#..#..#....#..#..#....#..#..#....#..#..#....#..#.
..#.#...#O#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#
.#...##..#..X...##..#..#...##..#..#...##..#..#...##..#..#...##..#.
..#.##.......#.X#.......#.##.......#.##.......#.##.......#.##.....
.#.#.#....#.#.#.#.O..#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#
.#........#.#........X.#........#.#........#.#........#.#........#
#.##...#...#.##...#...#.X#...#...#.##...#...#.##...#...#.##...#...
#...##....##...##....##...#X....##...##....##...##....##...##....#
.#..#...#.#.#..#...#.#.#..#...X.#.#..#...#.#.#..#...#.#.#..#...#.#
```

In this example, traversing the map using this slope would cause you to encounter `7` trees.

Starting at the top-left corner of your map and following a slope of right 3 and down 1, **how many trees would you encounter?**


> Your puzzle answer was `274`.


### Part Two

Time to check the rest of the slopes - you need to minimize the probability of a sudden arboreal stop, after all.

Determine the number of trees you would encounter if, for each of the following slopes, you start at the top-left corner and traverse the map all the way to the bottom:


+ Right 1, down 1.
+ Right 3, down 1. (This is the slope you already checked.)
+ Right 5, down 1.
+ Right 7, down 1.
+ Right 1, down 2.

In the above example, these slopes would find `2`, `7`, `3`, `4`, and `2` tree(s) respectively; multiplied together, these produce the answer `336`.

**What do you get if you multiply together the number of trees encountered on each of the listed slopes?**


> Your puzzle answer was `6050183040`.

## [Day 4: Passport Processing](https://adventofcode.com/2020/day/4)

You arrive at the airport only to realize that you grabbed your North Pole Credentials instead of your passport. While these documents are extremely similar, North Pole Credentials aren't issued by a country and therefore aren't actually valid documentation for travel in most of the world.

It seems like you're not the only one having problems, though; a very long line has formed for the automatic passport scanners, and the delay could upset your travel itinerary.

Due to some questionable network security, you realize you might be able to solve both of these problems at the same time.

The automatic passport scanners are slow because they're having trouble **detecting which passports have all required fields**. The expected fields are as follows:


+ `byr` (Birth Year)
+ `iyr` (Issue Year)
+ `eyr` (Expiration Year)
+ `hgt` (Height)
+ `hcl` (Hair Color)
+ `ecl` (Eye Color)
+ `pid` (Passport ID)
+ `cid` (Country ID)

Passport data is validated in batch files (your puzzle input). Each passport is represented as a sequence of `key:value` pairs separated by spaces or newlines. Passports are separated by blank lines.

Here is an example batch file containing four passports:


```
ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
byr:1937 iyr:2017 cid:147 hgt:183cm

iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
hcl:#cfa07d byr:1929

hcl:#ae17e1 iyr:2013
eyr:2024
ecl:brn pid:760753108 byr:1931
hgt:179cm

hcl:#cfa07d eyr:2025 pid:166559648
iyr:2011 ecl:brn hgt:59in
```

The first passport is **valid** - all eight fields are present. The second passport is **invalid** - it is missing `hgt` (the Height field).

The third passport is interesting; the **only missing field** is `cid`, so it looks like data from North Pole Credentials, not a passport at all! Surely, nobody would mind if you made the system temporarily ignore missing `cid` fields.  Treat this "passport" as **valid**.

The fourth passport is missing two fields, `cid` and `byr`. Missing `cid` is fine, but missing any other field is not, so this passport is **invalid**.

According to the above rules, your improved system would report `2` valid passports.

Count the number of **valid** passports - those that have all required fields. Treat `cid` as optional. **In your batch file, how many passports are valid?**


> Your puzzle answer was `230`.


### Part Two

The line is moving more quickly now, but you overhear airport security talking about how passports with invalid data are getting through. Better add some data validation, quick!

You can continue to ignore the `cid` field, but each other field has strict rules about what values are valid for automatic validation:


+ `byr` (Birth Year) - four digits; at least `1920` and at most `2002`.
+ `iyr` (Issue Year) - four digits; at least `2010` and at most `2020`.
+ `eyr` (Expiration Year) - four digits; at least `2020` and at most `2030`.
+ `hgt` (Height) - a number followed by either `cm` or `in`:

  + If `cm`, the number must be at least `150` and at most `193`.
  + If `in`, the number must be at least `59` and at most `76`.


+ `hcl` (Hair Color) - a `#` followed by exactly six characters `0`-`9` or `a`-`f`.
+ `ecl` (Eye Color) - exactly one of: `amb` `blu` `brn` `gry` `grn` `hzl` `oth`.
+ `pid` (Passport ID) - a nine-digit number, including leading zeroes.
+ `cid` (Country ID) - ignored, missing or not.

Your job is to count the passports where all required fields are both **present** and **valid** according to the above rules. Here are some example values:


```
byr valid:   2002
byr invalid: 2003

hgt valid:   60in
hgt valid:   190cm
hgt invalid: 190in
hgt invalid: 190

hcl valid:   #123abc
hcl invalid: #123abz
hcl invalid: 123abc

ecl valid:   brn
ecl invalid: wat

pid valid:   000000001
pid invalid: 0123456789
```

Here are some invalid passports:


```
eyr:1972 cid:100
hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926

iyr:2019
hcl:#602927 eyr:1967 hgt:170cm
ecl:grn pid:012533040 byr:1946

hcl:dab227 iyr:2012
ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277

hgt:59cm ecl:zzz
eyr:2038 hcl:74454a iyr:2023
pid:3556412378 byr:2007
```

Here are some valid passports:


```
pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980
hcl:#623a2f

eyr:2029 ecl:blu cid:129 byr:1989
iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm

hcl:#888785
hgt:164cm byr:2001 iyr:2015 cid:88
pid:545766238 ecl:hzl
eyr:2022

iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719
```

Count the number of **valid** passports - those that have all required fields **and valid values**. Continue to treat `cid` as optional. **In your batch file, how many passports are valid?**


> Your puzzle answer was `156`.

## [Day 5: Binary Boarding](https://adventofcode.com/2020/day/5)

You board your plane only to discover a new problem: you dropped your boarding pass! You aren't sure which seat is yours, and all of the flight attendants are busy with the flood of people that suddenly made it through passport control.

You write a quick program to use your phone's camera to scan all of the nearby boarding passes (your puzzle input); perhaps you can find your seat through process of elimination.

Instead of <a target="_blank" href="https://www.youtube.com/watch?v=oAHbLRjF0vo">zones or groups</a>, this airline uses **binary space partitioning** to seat people. A seat might be specified like `FBFBBFFRLR`, where `F` means "front", `B` means "back", `L` means "left", and `R` means "right".

The first 7 characters will either be `F` or `B`; these specify exactly one of the **128 rows** on the plane (numbered `0` through `127`). Each letter tells you which half of a region the given seat is in. Start with the whole list of rows; the first letter indicates whether the seat is in the **front** (`0` through `63`) or the **back** (`64` through `127`). The next letter indicates which half of that region the seat is in, and so on until you're left with exactly one row.

For example, consider just the first seven characters of `FBFBBFFRLR`:


+ Start by considering the whole range, rows `0` through `127`.
+ `F` means to take the **lower half**, keeping rows `0` through `63`.
+ `B` means to take the **upper half**, keeping rows `32` through `63`.
+ `F` means to take the **lower half**, keeping rows `32` through `47`.
+ `B` means to take the **upper half**, keeping rows `40` through `47`.
+ `B` keeps rows `44` through `47`.
+ `F` keeps rows `44` through `45`.
+ The final `F` keeps the lower of the two, **row `44`**.

The last three characters will be either `L` or `R`; these specify exactly one of the **8 columns** of seats on the plane (numbered `0` through `7`). The same process as above proceeds again, this time with only three steps.  `L` means to keep the **lower half**, while `R` means to keep the **upper half**.

For example, consider just the last 3 characters of `FBFBBFFRLR`:


+ Start by considering the whole range, columns `0` through `7`.
+ `R` means to take the **upper half**, keeping columns `4` through `7`.
+ `L` means to take the **lower half**, keeping columns `4` through `5`.
+ The final `R` keeps the upper of the two, **column `5`**.

So, decoding `FBFBBFFRLR` reveals that it is the seat at **row `44`, column `5`**.

Every seat also has a unique **seat ID**: multiply the row by 8, then add the column. In this example, the seat has ID `44 * 8 + 5 = 357`.

Here are some other boarding passes:


+ `BFFFBBFRRR`: row `70`, column `7`, seat ID `567`.
+ `FFFBBBFRRR`: row `14`, column `7`, seat ID `119`.
+ `BBFFBBFRLL`: row `102`, column `4`, seat ID `820`.

As a sanity check, look through your list of boarding passes. **What is the highest seat ID on a boarding pass?**


> Your puzzle answer was `806`.


### Part Two

**Ding!** The "fasten seat belt" signs have turned on. Time to find your seat.

It's a completely full flight, so your seat should be the only missing boarding pass in your list.  However, there's a catch: some of the seats at the very front and back of the plane don't exist on this aircraft, so they'll be missing from your list as well.

Your seat wasn't at the very front or back, though; the seats with IDs +1 and -1 from yours will be in your list.

**What is the ID of your seat?**


> Your puzzle answer was `562`.

## [Day 6: Custom Customs](https://adventofcode.com/2020/day/6)

As your flight approaches the regional airport where you'll switch to a much larger plane, [customs declaration forms](https://en.wikipedia.org/wiki/Customs_declaration) are distributed to the passengers.

The form asks a series of 26 yes-or-no questions marked `a` through `z`. All you need to do is identify the questions for which **anyone in your group** answers "yes". Since your group is just you, this doesn't take very long.

However, the person sitting next to you seems to be experiencing a language barrier and asks if you can help. For each of the people in their group, you write down the questions for which they answer "yes", one per line.  For example:


```
abcx
abcy
abcz
```

In this group, there are **`6`** questions to which anyone answered "yes": `a`, `b`, `c`, `x`, `y`, and `z`. (Duplicate answers to the same question don't count extra; each question counts at most once.)

Another group asks for your help, then another, and eventually you've collected answers from every group on the plane (your puzzle input). Each group's answers are separated by a blank line, and within each group, each person's answers are on a single line. For example:


```
abc

a
b
c

ab
ac

a
a
a
a

b
```

This list represents answers from five groups:


+ The first group contains one person who answered "yes" to **`3`** questions: `a`, `b`, and `c`.
+ The second group contains three people; combined, they answered "yes" to **`3`** questions: `a`, `b`, and `c`.
+ The third group contains two people; combined, they answered "yes" to **`3`** questions: `a`, `b`, and `c`.
+ The fourth group contains four people; combined, they answered "yes" to only **`1`** question, `a`.
+ The last group contains one person who answered "yes" to only **`1`** question, `b`.

In this example, the sum of these counts is `3 + 3 + 3 + 1 + 1` = **`11`**.

For each group, count the number of questions to which anyone answered "yes". **What is the sum of those counts?**


> Your puzzle answer was `6443`.


### Part Two

As you finish the last group's customs declaration, you notice that you misread one word in the instructions:

You don't need to identify the questions to which **anyone** answered "yes"; you need to identify the questions to which **everyone** answered "yes"!

Using the same  example as above:


```
abc

a
b
c

ab
ac

a
a
a
a

b
```

This list represents answers from five groups:


+ In the first group, everyone (all 1 person) answered "yes" to **`3`** questions: `a`, `b`, and `c`.
+ In the second group, there is **no** question to which everyone answered "yes".
+ In the third group, everyone answered yes to only **`1`** question, `a`. Since some people did not answer "yes" to `b` or `c`, they don't count.
+ In the fourth group, everyone answered yes to only **`1`** question, `a`.
+ In the fifth group, everyone (all 1 person) answered "yes" to **`1`** question, `b`.

In this example, the sum of these counts is `3 + 0 + 1 + 1 + 1` = **`6`**.

For each group, count the number of questions to which **everyone** answered "yes". **What is the sum of those counts?**


> Your puzzle answer was `3232`.

## [Day 7: Handy Haversacks](https://adventofcode.com/2020/day/7)

You land at the regional airport in time for your next flight. In fact, it looks like you'll even have time to grab some food: all flights are currently delayed due to **issues in luggage processing**.

Due to recent aviation regulations, many rules (your puzzle input) are being enforced about bags and their contents; bags must be color-coded and must contain specific quantities of other color-coded bags. Apparently, nobody responsible for these regulations considered how long they would take to enforce!

For example, consider the following rules:


```
light red bags contain 1 bright white bag, 2 muted yellow bags.
dark orange bags contain 3 bright white bags, 4 muted yellow bags.
bright white bags contain 1 shiny gold bag.
muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
dark olive bags contain 3 faded blue bags, 4 dotted black bags.
vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
faded blue bags contain no other bags.
dotted black bags contain no other bags.
```

These rules specify the required contents for 9 bag types. In this example, every `faded blue` bag is empty, every `vibrant plum` bag contains 11 bags (5 `faded blue` and 6 `dotted black`), and so on.

You have a `shiny gold` bag. If you wanted to carry it in at least one other bag, how many different bag colors would be valid for the outermost bag? (In other words: how many colors can, eventually, contain at least one `shiny gold` bag?)

In the above rules, the following options would be available to you:


+ A `bright white` bag, which can hold your `shiny gold` bag directly.
+ A `muted yellow` bag, which can hold your `shiny gold` bag directly, plus some other bags.
+ A `dark orange` bag, which can hold `bright white` and `muted yellow` bags, either of which could then hold your `shiny gold` bag.
+ A `light red` bag, which can hold `bright white` and `muted yellow` bags, either of which could then hold your `shiny gold` bag.

So, in this example, the number of bag colors that can eventually contain at least one `shiny gold` bag is `4`.

**How many bag colors can eventually contain at least one `shiny gold` bag?** (The list of rules is quite long; make sure you get all of it.)


> Your puzzle answer was `126`.


### Part Two

It's getting pretty expensive to fly these days - not because of ticket prices, but because of the ridiculous number of bags you need to buy!

Consider again your `shiny gold` bag and the rules from the above example:


+ `faded blue` bags contain `0` other bags.
+ `dotted black` bags contain `0` other bags.
+ `vibrant plum` bags contain `11` other bags: 5 `faded blue` bags and 6 `dotted black` bags.
+ `dark olive` bags contain `7` other bags: 3 `faded blue` bags and 4 `dotted black` bags.

So, a single `shiny gold` bag must contain 1 `dark olive` bag (and the 7 bags within it) plus 2 `vibrant plum` bags (and the 11 bags within **each** of those): `1 + 1*7 + 2 + 2*11` = `32` bags!

Of course, the actual rules have a small chance of going several levels deeper than this example; be sure to count all of the bags, even if the nesting becomes topologically impractical!

Here's another example:


```
shiny gold bags contain 2 dark red bags.
dark red bags contain 2 dark orange bags.
dark orange bags contain 2 dark yellow bags.
dark yellow bags contain 2 dark green bags.
dark green bags contain 2 dark blue bags.
dark blue bags contain 2 dark violet bags.
dark violet bags contain no other bags.
```

In this example, a single `shiny gold` bag must contain `126` other bags.

**How many individual bags are required inside your single `shiny gold` bag?**


> Your puzzle answer was `220149`.

## [Day 8: Handheld Halting](https://adventofcode.com/2020/day/8)

Your flight to the major airline hub reaches cruising altitude without incident.  While you consider checking the in-flight menu for one of those drinks that come with a little umbrella, you are interrupted by the kid sitting next to you.

Their <a target="_blank" href="https://en.wikipedia.org/wiki/Handheld_game_console">handheld game console</a> won't turn on! They ask if you can take a look.

You narrow the problem down to a strange **infinite loop** in the boot code (your puzzle input) of the device. You should be able to fix it, but first you need to be able to run the code in isolation.

The boot code is represented as a text file with one **instruction** per line of text. Each instruction consists of an **operation** (`acc`, `jmp`, or `nop`) and an **argument** (a signed number like `+4` or `-20`).


+ `acc` increases or decreases a single global value called the **accumulator** by the value given in the argument. For example, `acc +7` would increase the accumulator by 7. The accumulator starts at `0`. After an `acc` instruction, the instruction immediately below it is executed next.
+ `jmp` **jumps** to a new instruction relative to itself. The next instruction to execute is found using the argument as an **offset** from the `jmp` instruction; for example, `jmp +2` would skip the next instruction, `jmp +1` would continue to the instruction immediately below it, and `jmp -20` would cause the instruction 20 lines above to be executed next.
+ `nop` stands for **No OPeration** - it does nothing.  The instruction immediately below it is executed next.

For example, consider the following program:


```
nop +0
acc +1
jmp +4
acc +3
jmp -3
acc -99
acc +1
jmp -4
acc +6
```

These instructions are visited in this order:


```
nop +0  | 1
acc +1  | 2, 8(!)
jmp +4  | 3
acc +3  | 6
jmp -3  | 7
acc -99 |
acc +1  | 4
jmp -4  | 5
acc +6  |
```

First, the `nop +0` does nothing. Then, the accumulator is increased from 0 to 1 (`acc +1`) and `jmp +4` sets the next instruction to the other `acc +1` near the bottom. After it increases the accumulator from 1 to 2, `jmp -4` executes, setting the next instruction to the only `acc +3`. It sets the accumulator to 5, and `jmp -3` causes the program to continue back at the first `acc +1`.

This is an **infinite loop**: with this sequence of jumps, the program will run forever. The moment the program tries to run any instruction a second time, you know it will never terminate.

Immediately **before** the program would run an instruction a second time, the value in the accumulator is **`5`**.

Run your copy of the boot code. Immediately before any instruction is executed a second time, **what value is in the accumulator?**


> Your puzzle answer was `2014`.


### Part Two

After some careful analysis, you believe that **exactly one instruction is corrupted**.

Somewhere in the program, **either** a `jmp` is supposed to be a `nop`, **or** a `nop` is supposed to be a `jmp`. (No `acc` instructions were harmed in the corruption of this boot code.)

The program is supposed to terminate by **attempting to execute an instruction immediately after the last instruction in the file**. By changing exactly one `jmp` or `nop`, you can repair the boot code and make it terminate correctly.

For example, consider the same program from above:


```
nop +0
acc +1
jmp +4
acc +3
jmp -3
acc -99
acc +1
jmp -4
acc +6
```

If you change the first instruction from `nop +0` to `jmp +0`, it would create a single-instruction infinite loop, never leaving that instruction.  If you change almost any of the `jmp` instructions, the program will still eventually find another `jmp` instruction and loop forever.

However, if you change the second-to-last instruction (from `jmp -4` to `nop -4`), the program terminates! The instructions are visited in this order:


```
nop +0  | 1
acc +1  | 2
jmp +4  | 3
acc +3  |
jmp -3  |
acc -99 |
acc +1  | 4
nop -4  | 5
acc +6  | 6
```

After the last instruction (`acc +6`), the program terminates by attempting to run the instruction below the last instruction in the file.  With this change, after the program terminates, the accumulator contains the value **`8`** (`acc +1`, `acc +1`, `acc +6`).

Fix the program so that it terminates normally by changing exactly one `jmp` (to `nop`) or `nop` (to `jmp`). **What is the value of the accumulator after the program terminates?**


> Your puzzle answer was `2251`.

## [Day 9: Encoding Error](https://adventofcode.com/2020/day/9)

With your neighbor happily enjoying their video game, you turn your attention to an open data port on the little screen in the seat in front of you.

Though the port is non-standard, you manage to connect it to your computer through the clever use of several paperclips. Upon connection, the port outputs a series of numbers (your puzzle input).

The data appears to be encrypted with the eXchange-Masking Addition System (XMAS) which, conveniently for you, is an old cypher with an important weakness.

XMAS starts by transmitting a **preamble** of 25 numbers. After that, each number you receive should be the sum of any two of the 25 immediately previous numbers. The two numbers will have different values, and there might be more than one such pair.

For example, suppose your preamble consists of the numbers `1` through `25` in a random order. To be valid, the next number must be the sum of two of those numbers:


+ `26` would be a **valid** next number, as it could be `1` plus `25` (or many other pairs, like `2` and `24`).
+ `49` would be a **valid** next number, as it is the sum of `24` and `25`.
+ `100` would **not** be valid; no two of the previous 25 numbers sum to `100`.
+ `50` would also **not** be valid; although `25` appears in the previous 25 numbers, the two numbers in the pair must be different.

Suppose the 26th number is `45`, and the first number (no longer an option, as it is more than 25 numbers ago) was `20`. Now, for the next number to be valid, there needs to be some pair of numbers among `1`-`19`, `21`-`25`, or `45` that add up to it:


+ `26` would still be a **valid** next number, as `1` and `25` are still within the previous 25 numbers.
+ `65` would **not** be valid, as no two of the available numbers sum to it.
+ `64` and `66` would both be **valid**, as they are the result of `19+45` and `21+45` respectively.

Here is a larger example which only considers the previous **5** numbers (and has a preamble of length 5):


```
35
20
15
25
47
40
62
55
65
95
102
117
150
182
127
219
299
277
309
576
```

In this example, after the 5-number preamble, almost every number is the sum of two of the previous 5 numbers; the only number that does not follow this rule is **`127`**.

The first step of attacking the weakness in the XMAS data is to find the first number in the list (after the preamble) which is **not** the sum of two of the 25 numbers before it. **What is the first number that does not have this property?**


> Your puzzle answer was `21806024`.


### Part Two

The final step in breaking the XMAS encryption relies on the invalid number you just found: you must **find a contiguous set of at least two numbers** in your list which sum to the invalid number from step 1.

Again consider the above example:


```
35
20
15
25
47
40
62
55
65
95
102
117
150
182
127
219
299
277
309
576
```

In this list, adding up all of the numbers from `15` through `40` produces the invalid number from step 1, `127`. (Of course, the contiguous set of numbers in your actual list might be much longer.)

To find the **encryption weakness**, add together the **smallest** and **largest** number in this contiguous range; in this example, these are `15` and `47`, producing **`62`**.

**What is the encryption weakness in your XMAS-encrypted list of numbers?**


> Your puzzle answer was `2986195`.

## [Day 10: Adapter Array](https://adventofcode.com/2020/day/10)

Patched into the aircraft's data port, you discover weather forecasts of a massive tropical storm. Before you can figure out whether it will impact your vacation plans, however, your device suddenly turns off!

Its battery is dead.

You'll need to plug it in. There's only one problem: the charging outlet near your seat produces the wrong number of **jolts**. Always prepared, you make a list of all of the joltage adapters in your bag.

Each of your joltage adapters is rated for a specific **output joltage** (your puzzle input). Any given adapter can take an input `1`, `2`, or `3` jolts **lower** than its rating and still produce its rated output joltage.

In addition, your device has a built-in joltage adapter rated for **`3` jolts higher** than the highest-rated adapter in your bag. (If your adapter list were `3`, `9`, and `6`, your device's built-in adapter would be rated for `12` jolts.)

Treat the charging outlet near your seat as having an effective joltage rating of `0`.

Since you have some time to kill, you might as well test all of your adapters. Wouldn't want to get to your resort and realize you can't even charge your device!

If you **use every adapter in your bag** at once, what is the distribution of joltage differences between the charging outlet, the adapters, and your device?

For example, suppose that in your bag, you have adapters with the following joltage ratings:


```
16
10
15
5
1
11
7
19
6
12
4
```

With these adapters, your device's built-in joltage adapter would be rated for `19 + 3 = 22` jolts, 3 higher than the highest-rated adapter.

Because adapters can only connect to a source 1-3 jolts lower than its rating, in order to use every adapter, you'd need to choose them like this:


+ The charging outlet has an effective rating of `0` jolts, so the only adapters that could connect to it directly would need to have a joltage rating of `1`, `2`, or `3` jolts. Of these, only one you have is an adapter rated `1` jolt (difference of **`1`**).
+ From your `1`-jolt rated adapter, the only choice is your `4`-jolt rated adapter (difference of **`3`**).
+ From the `4`-jolt rated adapter, the adapters rated `5`, `6`, or `7` are valid choices. However, in order to not skip any adapters, you have to pick the adapter rated `5` jolts (difference of **`1`**).
+ Similarly, the next choices would need to be the adapter rated `6` and then the adapter rated `7` (with difference of **`1`** and **`1`**).
+ The only adapter that works with the `7`-jolt rated adapter is the one rated `10` jolts (difference of **`3`**).
+ From `10`, the choices are `11` or `12`; choose `11` (difference of **`1`**) and then `12` (difference of **`1`**).
+ After `12`, only valid adapter has a rating of `15` (difference of **`3`**), then `16` (difference of **`1`**), then `19` (difference of **`3`**).
+ Finally, your device's built-in adapter is always 3 higher than the highest adapter, so its rating is `22` jolts (always a difference of **`3`**).

In this example, when using every adapter, there are **`7`** differences of 1 jolt and **`5`** differences of 3 jolts.

Here is a larger example:


```
28
33
18
42
31
14
46
20
48
47
24
23
49
45
19
38
39
11
1
32
25
35
8
17
7
9
4
2
34
10
3
```

In this larger example, in a chain that uses all of the adapters, there are **`22`** differences of 1 jolt and **`10`** differences of 3 jolts.

Find a chain that uses all of your adapters to connect the charging outlet to your device's built-in adapter and count the joltage differences between the charging outlet, the adapters, and your device. **What is the number of 1-jolt differences multiplied by the number of 3-jolt differences?**


> Your puzzle answer was `2030`.


### Part Two

To completely determine whether you have enough adapters, you'll need to figure out how many different ways they can be arranged. Every arrangement needs to connect the charging outlet to your device. The previous rules about when adapters can successfully connect still apply.

The first example above (the one that starts with `16`, `10`, `15`) supports the following arrangements:


```
(0), 1, 4, 5, 6, 7, 10, 11, 12, 15, 16, 19, (22)
(0), 1, 4, 5, 6, 7, 10, 12, 15, 16, 19, (22)
(0), 1, 4, 5, 7, 10, 11, 12, 15, 16, 19, (22)
(0), 1, 4, 5, 7, 10, 12, 15, 16, 19, (22)
(0), 1, 4, 6, 7, 10, 11, 12, 15, 16, 19, (22)
(0), 1, 4, 6, 7, 10, 12, 15, 16, 19, (22)
(0), 1, 4, 7, 10, 11, 12, 15, 16, 19, (22)
(0), 1, 4, 7, 10, 12, 15, 16, 19, (22)
```

(The charging outlet and your device's built-in adapter are shown in parentheses.) Given the adapters from the first example, the total number of arrangements that connect the charging outlet to your device is **`8`**.

The second example above (the one that starts with `28`, `33`, `18`) has many arrangements. Here are a few:


```
(0), 1, 2, 3, 4, 7, 8, 9, 10, 11, 14, 17, 18, 19, 20, 23, 24, 25, 28, 31,
32, 33, 34, 35, 38, 39, 42, 45, 46, 47, 48, 49, (52)

(0), 1, 2, 3, 4, 7, 8, 9, 10, 11, 14, 17, 18, 19, 20, 23, 24, 25, 28, 31,
32, 33, 34, 35, 38, 39, 42, 45, 46, 47, 49, (52)

(0), 1, 2, 3, 4, 7, 8, 9, 10, 11, 14, 17, 18, 19, 20, 23, 24, 25, 28, 31,
32, 33, 34, 35, 38, 39, 42, 45, 46, 48, 49, (52)

(0), 1, 2, 3, 4, 7, 8, 9, 10, 11, 14, 17, 18, 19, 20, 23, 24, 25, 28, 31,
32, 33, 34, 35, 38, 39, 42, 45, 46, 49, (52)

(0), 1, 2, 3, 4, 7, 8, 9, 10, 11, 14, 17, 18, 19, 20, 23, 24, 25, 28, 31,
32, 33, 34, 35, 38, 39, 42, 45, 47, 48, 49, (52)

(0), 3, 4, 7, 10, 11, 14, 17, 20, 23, 25, 28, 31, 34, 35, 38, 39, 42, 45,
46, 48, 49, (52)

(0), 3, 4, 7, 10, 11, 14, 17, 20, 23, 25, 28, 31, 34, 35, 38, 39, 42, 45,
46, 49, (52)

(0), 3, 4, 7, 10, 11, 14, 17, 20, 23, 25, 28, 31, 34, 35, 38, 39, 42, 45,
47, 48, 49, (52)

(0), 3, 4, 7, 10, 11, 14, 17, 20, 23, 25, 28, 31, 34, 35, 38, 39, 42, 45,
47, 49, (52)

(0), 3, 4, 7, 10, 11, 14, 17, 20, 23, 25, 28, 31, 34, 35, 38, 39, 42, 45,
48, 49, (52)
```

In total, this set of adapters can connect the charging outlet to your device in **`19208`** distinct arrangements.

You glance back down at your bag and try to remember why you brought so many adapters; there must be **more than a trillion** valid ways to arrange them! Surely, there must be an efficient way to count the arrangements.

**What is the total number of distinct ways you can arrange the adapters to connect the charging outlet to your device?**


> Your puzzle answer was `42313823813632`.

## [Day 11: Seating System](https://adventofcode.com/2020/day/11)

Your plane lands with plenty of time to spare. The final leg of your journey is a ferry that goes directly to the tropical island where you can finally start your vacation. As you reach the waiting area to board the ferry, you realize you're so early, nobody else has even arrived yet!

By modeling the process people use to choose (or abandon) their seat in the waiting area, you're pretty sure you can predict the best place to sit. You make a quick map of the seat layout (your puzzle input).

The seat layout fits neatly on a grid. Each position is either floor (`.`), an empty seat (`L`), or an occupied seat (`#`). For example, the initial seat layout might look like this:


```
L.LL.LL.LL
LLLLLLL.LL
L.L.L..L..
LLLL.LL.LL
L.LL.LL.LL
L.LLLLL.LL
..L.L.....
LLLLLLLLLL
L.LLLLLL.L
L.LLLLL.LL
```

Now, you just need to model the people who will be arriving shortly. Fortunately, people are entirely predictable and always follow a simple set of rules. All decisions are based on the **number of occupied seats** adjacent to a given seat (one of the eight positions immediately up, down, left, right, or diagonal from the seat). The following rules are applied to every seat simultaneously:


+ If a seat is **empty** (`L`) and there are **no** occupied seats adjacent to it, the seat becomes **occupied**.
+ If a seat is **occupied** (`#`) and **four or more** seats adjacent to it are also occupied, the seat becomes **empty**.
+ Otherwise, the seat's state does not change.

Floor (`.`) never changes; seats don't move, and nobody sits on the floor.

After one round of these rules, every seat in the example layout becomes occupied:


```
#.##.##.##
#######.##
#.#.#..#..
####.##.##
#.##.##.##
#.#####.##
..#.#.....
##########
#.######.#
#.#####.##
```

After a second round, the seats with four or more occupied adjacent seats become empty again:


```
#.LL.L#.##
#LLLLLL.L#
L.L.L..L..
#LLL.LL.L#
#.LL.LL.LL
#.LLLL#.##
..L.L.....
#LLLLLLLL#
#.LLLLLL.L
#.#LLLL.##
```

This process continues for three more rounds:


```
#.##.L#.##
#L###LL.L#
L.#.#..#..
#L##.##.L#
#.##.LL.LL
#.###L#.##
..#.#.....
#L######L#
#.LL###L.L
#.#L###.##
```


```
#.#L.L#.##
#LLL#LL.L#
L.L.L..#..
#LLL.##.L#
#.LL.LL.LL
#.LL#L#.##
..L.L.....
#L#LLLL#L#
#.LLLLLL.L
#.#L#L#.##
```


```
#.#L.L#.##
#LLL#LL.L#
L.#.L..#..
#L##.##.L#
#.#L.LL.LL
#.#L#L#.##
..L.L.....
#L#L##L#L#
#.LLLLLL.L
#.#L#L#.##
```

At this point, something interesting happens: the chaos stabilizes and further applications of these rules cause no seats to change state! Once people stop moving around, you count **`37`** occupied seats.

Simulate your seating area by applying the seating rules repeatedly until no seats change state. **How many seats end up occupied?**


> Your puzzle answer was `2299`.


### Part Two

As soon as people start to arrive, you realize your mistake. People don't just care about adjacent seats - they care about **the first seat they can see** in each of those eight directions!

Now, instead of considering just the eight immediately adjacent seats, consider the **first seat** in each of those eight directions. For example, the empty seat below would see **eight** occupied seats:


```
.......#.
...#.....
.#.......
.........
..#L....#
....#....
.........
#........
...#.....
```

The leftmost empty seat below would only see **one** empty seat, but cannot see any of the occupied ones:


```
.............
.L.L.#.#.#.#.
.............
```

The empty seat below would see **no** occupied seats:


```
.##.##.
#.#.#.#
##...##
...L...
##...##
#.#.#.#
.##.##.
```

Also, people seem to be more tolerant than you expected: it now takes **five or more** visible occupied seats for an occupied seat to become empty (rather than **four or more** from the previous rules). The other rules still apply: empty seats that see no occupied seats become occupied, seats matching no rule don't change, and floor never changes.

Given the same starting layout as above, these new rules cause the seating area to shift around as follows:


```
L.LL.LL.LL
LLLLLLL.LL
L.L.L..L..
LLLL.LL.LL
L.LL.LL.LL
L.LLLLL.LL
..L.L.....
LLLLLLLLLL
L.LLLLLL.L
L.LLLLL.LL
```


```
#.##.##.##
#######.##
#.#.#..#..
####.##.##
#.##.##.##
#.#####.##
..#.#.....
##########
#.######.#
#.#####.##
```


```
#.LL.LL.L#
#LLLLLL.LL
L.L.L..L..
LLLL.LL.LL
L.LL.LL.LL
L.LLLLL.LL
..L.L.....
LLLLLLLLL#
#.LLLLLL.L
#.LLLLL.L#
```


```
#.L#.##.L#
#L#####.LL
L.#.#..#..
##L#.##.##
#.##.#L.##
#.#####.#L
..#.#.....
LLL####LL#
#.L#####.L
#.L####.L#
```


```
#.L#.L#.L#
#LLLLLL.LL
L.L.L..#..
##LL.LL.L#
L.LL.LL.L#
#.LLLLL.LL
..L.L.....
LLLLLLLLL#
#.LLLLL#.L
#.L#LL#.L#
```


```
#.L#.L#.L#
#LLLLLL.LL
L.L.L..#..
##L#.#L.L#
L.L#.#L.L#
#.L####.LL
..#.#.....
LLL###LLL#
#.LLLLL#.L
#.L#LL#.L#
```


```
#.L#.L#.L#
#LLLLLL.LL
L.L.L..#..
##L#.#L.L#
L.L#.LL.L#
#.LLLL#.LL
..#.L.....
LLL###LLL#
#.LLLLL#.L
#.L#LL#.L#
```

Again, at this point, people stop shifting around and the seating area reaches equilibrium. Once this occurs, you count **`26`** occupied seats.

Given the new visibility method and the rule change for occupied seats becoming empty, once equilibrium is reached, **how many seats end up occupied?**


> Your puzzle answer was `2047`.

## [Day 12: Rain Risk](https://adventofcode.com/2020/day/12)

Your ferry made decent progress toward the island, but the storm came in faster than anyone expected. The ferry needs to take **evasive actions**!

Unfortunately, the ship's navigation computer seems to be malfunctioning; rather than giving a route directly to safety, it produced extremely circuitous instructions. When the captain uses the [PA system](https://en.wikipedia.org/wiki/Public_address_system) to ask if anyone can help, you quickly volunteer.

The navigation instructions (your puzzle input) consists of a sequence of single-character **actions** paired with integer input **values**. After staring at them for a few minutes, you work out what they probably mean:


+ Action **`N`** means to move **north** by the given value.
+ Action **`S`** means to move **south** by the given value.
+ Action **`E`** means to move **east** by the given value.
+ Action **`W`** means to move **west** by the given value.
+ Action **`L`** means to turn **left** the given number of degrees.
+ Action **`R`** means to turn **right** the given number of degrees.
+ Action **`F`** means to move **forward** by the given value in the direction the ship is currently facing.

The ship starts by facing **east**. Only the `L` and `R` actions change the direction the ship is facing. (That is, if the ship is facing east and the next instruction is `N10`, the ship would move north 10 units, but would still move east if the following action were `F`.)

For example:


```
F10
N3
F7
R90
F11
```

These instructions would be handled as follows:


+ `F10` would move the ship 10 units east (because the ship starts by facing east) to **east 10, north 0**.
+ `N3` would move the ship 3 units north to **east 10, north 3**.
+ `F7` would move the ship another 7 units east (because the ship is still facing east) to **east 17, north 3**.
+ `R90` would cause the ship to turn right by 90 degrees and face **south**; it remains at **east 17, north 3**.
+ `F11` would move the ship 11 units south to **east 17, south 8**.

At the end of these instructions, the ship's [Manhattan distance](https://en.wikipedia.org/wiki/Manhattan_distance) (sum of the absolute values of its east/west position and its north/south position) from its starting position is `17 + 8` = **`25`**.

Figure out where the navigation instructions lead. **What is the Manhattan distance between that location and the ship's starting position?**


> Your puzzle answer was `759`.


### Part Two

Before you can give the destination to the captain, you realize that the actual action meanings were printed on the back of the instructions the whole time.

Almost all of the actions indicate how to move a **waypoint** which is relative to the ship's position:


+ Action **`N`** means to move the waypoint **north** by the given value.
+ Action **`S`** means to move the waypoint **south** by the given value.
+ Action **`E`** means to move the waypoint **east** by the given value.
+ Action **`W`** means to move the waypoint **west** by the given value.
+ Action **`L`** means to rotate the waypoint around the ship **left** (**counter-clockwise**) the given number of degrees.
+ Action **`R`** means to rotate the waypoint around the ship **right** (**clockwise**) the given number of degrees.
+ Action **`F`** means to move **forward** to the waypoint a number of times equal to the given value.

The waypoint starts **10 units east and 1 unit north** relative to the ship. The waypoint is relative to the ship; that is, if the ship moves, the waypoint moves with it.

For example, using the same instructions as above:


+ `F10` moves the ship to the waypoint 10 times (a total of **100 units east and 10 units north**), leaving the ship at **east 100, north 10**. The waypoint stays 10 units east and 1 unit north of the ship.
+ `N3` moves the waypoint 3 units north to **10 units east and 4 units north of the ship**. The ship remains at **east 100, north 10**.
+ `F7` moves the ship to the waypoint 7 times (a total of **70 units east and 28 units north**), leaving the ship at **east 170, north 38**. The waypoint stays 10 units east and 4 units north of the ship.
+ `R90` rotates the waypoint around the ship clockwise 90 degrees, moving it to **4 units east and 10 units south of the ship**. The ship remains at **east 170, north 38**.
+ `F11` moves the ship to the waypoint 11 times (a total of **44 units east and 110 units south**), leaving the ship at **east 214, south 72**. The waypoint stays 4 units east and 10 units south of the ship.

After these operations, the ship's Manhattan distance from its starting position is `214 + 72` = **`286`**.

Figure out where the navigation instructions actually lead. **What is the Manhattan distance between that location and the ship's starting position?**


> Your puzzle answer was `45763`.

## [Day 13: Shuttle Search](https://adventofcode.com/2020/day/13)

Your ferry can make it safely to a nearby port, but it won't get much further. When you call to book another ship, you discover that no ships embark from that port to your vacation island. You'll need to get from the port to the nearest airport.

Fortunately, a shuttle bus service is available to bring you from the sea port to the airport!  Each bus has an ID number that also indicates **how often the bus leaves for the airport**.

Bus schedules are defined based on a **timestamp** that measures the **number of minutes** since some fixed reference point in the past. At timestamp `0`, every bus simultaneously departed from the sea port. After that, each bus travels to the airport, then various other locations, and finally returns to the sea port to repeat its journey forever.

The time this loop takes a particular bus is also its ID number: the bus with ID `5` departs from the sea port at timestamps `0`, `5`, `10`, `15`, and so on. The bus with ID `11` departs at `0`, `11`, `22`, `33`, and so on. If you are there when the bus departs, you can ride that bus to the airport!

Your notes (your puzzle input) consist of two lines.  The first line is your estimate of the **earliest timestamp you could depart on a bus**. The second line lists the bus IDs that are in service according to the shuttle company; entries that show `x` must be out of service, so you decide to ignore them.

To save time once you arrive, your goal is to figure out **the earliest bus you can take to the airport**. (There will be exactly one such bus.)

For example, suppose you have the following notes:


```
939
7,13,x,x,59,x,31,19
```

Here, the earliest timestamp you could depart is `939`, and the bus IDs in service are `7`, `13`, `59`, `31`, and `19`. Near timestamp `939`, these bus IDs depart at the times marked `D`:


```
time   bus 7   bus 13  bus 59  bus 31  bus 19
929      .       .       .       .       .
930      .       .       .       D       .
931      D       .       .       .       D
932      .       .       .       .       .
933      .       .       .       .       .
934      .       .       .       .       .
935      .       .       .       .       .
936      .       D       .       .       .
937      .       .       .       .       .
938      D       .       .       .       .
939      .       .       .       .       .
940      .       .       .       .       .
941      .       .       .       .       .
942      .       .       .       .       .
943      .       .       .       .       .
944      .       .       D       .       .
945      D       .       .       .       .
946      .       .       .       .       .
947      .       .       .       .       .
948      .       .       .       .       .
949      .       D       .       .       .
```

The earliest bus you could take is bus ID `59`. It doesn't depart until timestamp `944`, so you would need to wait `944 - 939 = 5` minutes before it departs. Multiplying the bus ID by the number of minutes you'd need to wait gives **`295`**.

**What is the ID of the earliest bus you can take to the airport multiplied by the number of minutes you'll need to wait for that bus?**


> Your puzzle answer was `2215`.


### Part Two

The shuttle company is running a contest: one gold coin for anyone that can find the earliest timestamp such that the first bus ID departs at that time and each subsequent listed bus ID departs at that subsequent minute. (The first line in your input is no longer relevant.)

For example, suppose you have the same list of bus IDs as above:

`7,13,x,x,59,x,31,19`
An `x` in the schedule means there are no constraints on what bus IDs must depart at that time.

This means you are looking for the earliest timestamp (called `t`) such that:


+ Bus ID `7` departs at timestamp `t`.
+ Bus ID `13` departs one minute after timestamp `t`.
+ There are no requirements or restrictions on departures at two or three minutes after timestamp `t`.
+ Bus ID `59` departs four minutes after timestamp `t`.
+ There are no requirements or restrictions on departures at five minutes after timestamp `t`.
+ Bus ID `31` departs six minutes after timestamp `t`.
+ Bus ID `19` departs seven minutes after timestamp `t`.

The only bus departures that matter are the listed bus IDs at their specific offsets from `t`. Those bus IDs can depart at other times, and other bus IDs can depart at those times.  For example, in the list above, because bus ID `19` must depart seven minutes after the timestamp at which bus ID `7` departs, bus ID `7` will always **also** be departing with bus ID `19` at seven minutes after timestamp `t`.

In this example, the earliest timestamp at which this occurs is **`1068781`**:


```
time     bus 7   bus 13  bus 59  bus 31  bus 19
1068773    .       .       .       .       .
1068774    D       .       .       .       .
1068775    .       .       .       .       .
1068776    .       .       .       .       .
1068777    .       .       .       .       .
1068778    .       .       .       .       .
1068779    .       .       .       .       .
1068780    .       .       .       .       .
1068781    D       .       .       .       .
1068782    .       D       .       .       .
1068783    .       .       .       .       .
1068784    .       .       .       .       .
1068785    .       .       D       .       .
1068786    .       .       .       .       .
1068787    .       .       .       D       .
1068788    D       .       .       .       D
1068789    .       .       .       .       .
1068790    .       .       .       .       .
1068791    .       .       .       .       .
1068792    .       .       .       .       .
1068793    .       .       .       .       .
1068794    .       .       .       .       .
1068795    D       D       .       .       .
1068796    .       .       .       .       .
1068797    .       .       .       .       .
```

In the above example, bus ID `7` departs at timestamp `1068788` (seven minutes after `t`). This is fine; the only requirement on that minute is that bus ID `19` departs then, and it does.

Here are some other examples:


+ The earliest timestamp that matches the list `17,x,13,19` is **`3417`**.
+ `67,7,59,61` first occurs at timestamp **`754018`**.
+ `67,x,7,59,61` first occurs at timestamp **`779210`**.
+ `67,7,x,59,61` first occurs at timestamp **`1261476`**.
+ `1789,37,47,1889` first occurs at timestamp **`1202161486`**.

However, with so many bus IDs in your list, surely the actual earliest timestamp will be larger than `100000000000000`!

**What is the earliest timestamp such that all of the listed bus IDs depart at offsets matching their positions in the list?**


> Your puzzle answer was `1058443396696792`.

## [Day 14: Docking Data](https://adventofcode.com/2020/day/14)

As your ferry approaches the sea port, the captain asks for your help again. The computer system that runs this port isn't compatible with the docking program on the ferry, so the docking parameters aren't being correctly initialized in the docking program's memory.

After a brief inspection, you discover that the sea port's computer system uses a strange [bitmask](https://en.wikipedia.org/wiki/Mask_(computing)) system in its initialization program. Although you don't have the correct decoder chip handy, you can emulate it in software!

The initialization program (your puzzle input) can either update the bitmask or write a value to memory.  Values and memory addresses are both 36-bit unsigned integers.  For example, ignoring bitmasks for a moment, a line like `mem[8] = 11` would write the value `11` to memory address `8`.

The bitmask is always given as a string of 36 bits, written with the most significant bit (representing `2^35`) on the left and the least significant bit (`2^0`, that is, the `1`s bit) on the right. The current bitmask is applied to values immediately before they are written to memory: a `0` or `1` overwrites the corresponding bit in the value, while an `X` leaves the bit in the value unchanged.

For example, consider the following program:


```
mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
mem[8] = 11
mem[7] = 101
mem[8] = 0
```

This program starts by specifying a bitmask (`mask = ....`). The mask it specifies will overwrite two bits in every written value: the `2`s bit is overwritten with `0`, and the `64`s bit is overwritten with `1`.

The program then attempts to write the value `11` to memory address `8`. By expanding everything out to individual bits, the mask is applied as follows:


```
value:  000000000000000000000000000000001011  (decimal 11)
mask:   XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
result: 000000000000000000000000000001001001  (decimal 73)
```

So, because of the mask, the value `73` is written to memory address `8` instead. Then, the program tries to write `101` to address `7`:


```
value:  000000000000000000000000000001100101  (decimal 101)
mask:   XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
result: 000000000000000000000000000001100101  (decimal 101)
```

This time, the mask has no effect, as the bits it overwrote were already the values the mask tried to set. Finally, the program tries to write `0` to address `8`:


```
value:  000000000000000000000000000000000000  (decimal 0)
mask:   XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
result: 000000000000000000000000000001000000  (decimal 64)
```

`64` is written to address `8` instead, overwriting the value that was there previously.

To initialize your ferry's docking program, you need the sum of all values left in memory after the initialization program completes. (The entire 36-bit address space begins initialized to the value `0` at every address.) In the above example, only two values in memory are not zero - `101` (at address `7`) and `64` (at address `8`) - producing a sum of **`165`**.

Execute the initialization program. **What is the sum of all values left in memory after it completes?**


> Your puzzle answer was `13727901897109`.


### Part Two

For some reason, the sea port's computer system still can't communicate with your ferry's docking program. It must be using **version 2** of the decoder chip!

A version 2 decoder chip doesn't modify the values being written at all.  Instead, it acts as a [memory address decoder](https://www.youtube.com/watch?v=PvfhANgLrm4). Immediately before a value is written to memory, each bit in the bitmask modifies the corresponding bit of the destination **memory address** in the following way:


+ If the bitmask bit is `0`, the corresponding memory address bit is **unchanged**.
+ If the bitmask bit is `1`, the corresponding memory address bit is **overwritten with `1`**.
+ If the bitmask bit is `X`, the corresponding memory address bit is **floating**.

A **floating** bit is not connected to anything and instead fluctuates unpredictably. In practice, this means the floating bits will take on **all possible values**, potentially causing many memory addresses to be written all at once!

For example, consider the following program:


```
mask = 000000000000000000000000000000X1001X
mem[42] = 100
mask = 00000000000000000000000000000000X0XX
mem[26] = 1
```

When this program goes to write to memory address `42`, it first applies the bitmask:


```
address: 000000000000000000000000000000101010  (decimal 42)
mask:    000000000000000000000000000000X1001X
result:  000000000000000000000000000000X1101X
```

After applying the mask, four bits are overwritten, three of which are different, and two of which are **floating**. Floating bits take on every possible combination of values; with two floating bits, four actual memory addresses are written:


```
000000000000000000000000000000011010  (decimal 26)
000000000000000000000000000000011011  (decimal 27)
000000000000000000000000000000111010  (decimal 58)
000000000000000000000000000000111011  (decimal 59)
```

Next, the program is about to write to memory address `26` with a different bitmask:


```
address: 000000000000000000000000000000011010  (decimal 26)
mask:    00000000000000000000000000000000X0XX
result:  00000000000000000000000000000001X0XX
```

This results in an address with three floating bits, causing writes to **eight** memory addresses:


```
000000000000000000000000000000010000  (decimal 16)
000000000000000000000000000000010001  (decimal 17)
000000000000000000000000000000010010  (decimal 18)
000000000000000000000000000000010011  (decimal 19)
000000000000000000000000000000011000  (decimal 24)
000000000000000000000000000000011001  (decimal 25)
000000000000000000000000000000011010  (decimal 26)
000000000000000000000000000000011011  (decimal 27)
```

The entire 36-bit address space still begins initialized to the value 0 at every address, and you still need the sum of all values left in memory at the end of the program.  In this example, the sum is **`208`**.

Execute the initialization program using an emulator for a version 2 decoder chip. **What is the sum of all values left in memory after it completes?**


> Your puzzle answer was `5579916171823`.

## [Day 15: Rambunctious Recitation](https://adventofcode.com/2020/day/15)

You catch the airport shuttle and try to book a new flight to your vacation island. Due to the storm, all direct flights have been cancelled, but a route is available to get around the storm. You take it.

While you wait for your flight, you decide to check in with the Elves back at the North Pole. They're playing a **memory game** and are ever so excited to explain the rules!

In this game, the players take turns saying **numbers**. They begin by taking turns reading from a list of **starting numbers** (your puzzle input). Then, each turn consists of considering the **most recently spoken number**:


+ If that was the **first** time the number has been spoken, the current player says **`0`**.
+ Otherwise, the number had been spoken before; the current player announces **how many turns apart** the number is from when it was previously spoken.

So, after the starting numbers, each turn results in that player speaking aloud either **`0`** (if the last number is new) or an **age** (if the last number is a repeat).

For example, suppose the starting numbers are `0,3,6`:


+ **Turn 1**: The `1`st number spoken is a starting number, **`0`**.
+ **Turn 2**: The `2`nd number spoken is a starting number, **`3`**.
+ **Turn 3**: The `3`rd number spoken is a starting number, **`6`**.
+ **Turn 4**: Now, consider the last number spoken, `6`. Since that was the first time the number had been spoken, the `4`th number spoken is **`0`**.
+ **Turn 5**: Next, again consider the last number spoken, `0`. Since it **had** been spoken before, the next number to speak is the difference between the turn number when it was last spoken (the previous turn, `4`) and the turn number of the time it was most recently spoken before then (turn `1`). Thus, the `5`th number spoken is `4 - 1`, **`3`**.
+ **Turn 6**: The last number spoken, `3` had also been spoken before, most recently on turns `5` and `2`. So, the `6`th number spoken is `5 - 2`, **`3`**.
+ **Turn 7**: Since `3` was just spoken twice in a row, and the last two turns are `1` turn apart, the `7`th number spoken is **`1`**.
+ **Turn 8**: Since `1` is new, the `8`th number spoken is **`0`**.
+ **Turn 9**: `0` was last spoken on turns `8` and `4`, so the `9`th number spoken is the difference between them, **`4`**.
+ **Turn 10**: `4` is new, so the `10`th number spoken is **`0`**.

(The game ends when the Elves get sick of playing or dinner is ready, whichever comes first.)

Their question for you is: what will be the **`2020`th** number spoken? In the example above, the `2020`th number spoken will be `436`.

Here are a few more examples:


+ Given the starting numbers `1,3,2`, the `2020`th number spoken is `1`.
+ Given the starting numbers `2,1,3`, the `2020`th number spoken is `10`.
+ Given the starting numbers `1,2,3`, the `2020`th number spoken is `27`.
+ Given the starting numbers `2,3,1`, the `2020`th number spoken is `78`.
+ Given the starting numbers `3,2,1`, the `2020`th number spoken is `438`.
+ Given the starting numbers `3,1,2`, the `2020`th number spoken is `1836`.

Given your starting numbers, **what will be the `2020`th number spoken?**


> Your puzzle answer was `1194`.


### Part Two

Impressed, the Elves issue you a challenge: determine the `30000000`th number spoken. For example, given the same starting numbers as above:


+ Given `0,3,6`, the `30000000`th number spoken is `175594`.
+ Given `1,3,2`, the `30000000`th number spoken is `2578`.
+ Given `2,1,3`, the `30000000`th number spoken is `3544142`.
+ Given `1,2,3`, the `30000000`th number spoken is `261214`.
+ Given `2,3,1`, the `30000000`th number spoken is `6895259`.
+ Given `3,2,1`, the `30000000`th number spoken is `18`.
+ Given `3,1,2`, the `30000000`th number spoken is `362`.

Given your starting numbers, **what will be the `30000000`th number spoken?**


> Your puzzle answer was `48710`.

## [Day 16: Ticket Translation](https://adventofcode.com/2020/day/16)

As you're walking to yet another connecting flight, you realize that one of the legs of your re-routed trip coming up is on a high-speed train. However, the train ticket you were given is in a language you don't understand. You should probably figure out what it says before you get to the train station after the next flight.

Unfortunately, you can't actually **read** the words on the ticket. You can, however, read the numbers, and so you figure out **the fields these tickets must have** and **the valid ranges** for values in those fields.

You collect the **rules for ticket fields**, the **numbers on your ticket**, and the **numbers on other nearby tickets** for the same train service (via the airport security cameras) together into a single document you can reference (your puzzle input).

The **rules for ticket fields** specify a list of fields that exist **somewhere** on the ticket and the **valid ranges of values** for each field. For example, a rule like `class: 1-3 or 5-7` means that one of the fields in every ticket is named `class` and can be any value in the ranges `1-3` or `5-7` (inclusive, such that `3` and `5` are both valid in this field, but `4` is not).

Each ticket is represented by a single line of comma-separated values. The values are the numbers on the ticket in the order they appear; every ticket has the same format. For example, consider this ticket:


```
.--------------------------------------------------------.
| ????: 101    ?????: 102   ??????????: 103     ???: 104 |
|                                                        |
| ??: 301  ??: 302             ???????: 303      ??????? |
| ??: 401  ??: 402           ???? ????: 403    ????????? |
'--------------------------------------------------------'
```

Here, `?` represents text in a language you don't understand. This ticket might be represented as `101,102,103,104,301,302,303,401,402,403`; of course, the actual train tickets you're looking at are **much** more complicated. In any case, you've extracted just the numbers in such a way that the first number is always the same specific field, the second number is always a different specific field, and so on - you just don't know what each position actually means!

Start by determining which tickets are **completely invalid**; these are tickets that contain values which **aren't valid for any field**. Ignore **your ticket** for now.

For example, suppose you have the following notes:


```
class: 1-3 or 5-7
row: 6-11 or 33-44
seat: 13-40 or 45-50

your ticket:
7,1,14

nearby tickets:
7,3,47
40,4,50
55,2,20
38,6,12
```

It doesn't matter which position corresponds to which field; you can identify invalid **nearby tickets** by considering only whether tickets contain **values that are not valid for any field**. In this example, the values on the first **nearby ticket** are all valid for at least one field. This is not true of the other three **nearby tickets**: the values `4`, `55`, and `12` are are not valid for any field. Adding together all of the invalid values produces your **ticket scanning error rate**: `4 + 55 + 12` = **`71`**.

Consider the validity of the **nearby tickets** you scanned. **What is your ticket scanning error rate?**


> Your puzzle answer was `22000`.


### Part Two

Now that you've identified which tickets contain invalid values, **discard those tickets entirely**. Use the remaining valid tickets to determine which field is which.

Using the valid ranges for each field, determine what order the fields appear on the tickets. The order is consistent between all tickets: if `seat` is the third field, it is the third field on every ticket, including **your ticket**.

For example, suppose you have the following notes:


```
class: 0-1 or 4-19
row: 0-5 or 8-19
seat: 0-13 or 16-19

your ticket:
11,12,13

nearby tickets:
3,9,18
15,1,5
5,14,9
```

Based on the **nearby tickets** in the above example, the first position must be `row`, the second position must be `class`, and the third position must be `seat`; you can conclude that in **your ticket**, `class` is `12`, `row` is `11`, and `seat` is `13`.

Once you work out which field is which, look for the six fields on **your ticket** that start with the word `departure`. **What do you get if you multiply those six values together?**


> Your puzzle answer was `410460648673`.

## [Day 17: Conway Cubes](https://adventofcode.com/2020/day/17)

As your flight slowly drifts through the sky, the Elves at the Mythical Information Bureau at the North Pole contact you. They'd like some help debugging a malfunctioning experimental energy source aboard one of their super-secret imaging satellites.

The experimental energy source is based on cutting-edge technology: a set of Conway Cubes contained in a pocket dimension! When you hear it's having problems, you can't help but agree to take a look.

The pocket dimension contains an infinite 3-dimensional grid. At every integer 3-dimensional coordinate (`x,y,z`), there exists a single cube which is either **active** or **inactive**.

In the initial state of the pocket dimension, almost all cubes start **inactive**. The only exception to this is a small flat region of cubes (your puzzle input); the cubes in this region start in the specified **active** (`#`) or **inactive** (`.`) state.

The energy source then proceeds to boot up by executing six **cycles**.

Each cube only ever considers its **neighbors**: any of the 26 other cubes where any of their coordinates differ by at most `1`. For example, given the cube at `x=1,y=2,z=3`, its neighbors include the cube at `x=2,y=2,z=2`, the cube at `x=0,y=2,z=3`, and so on.

During a cycle, **all** cubes **simultaneously** change their state according to the following rules:


+ If a cube is **active** and **exactly `2` or `3`** of its neighbors are also active, the cube remains **active**. Otherwise, the cube becomes **inactive**.
+ If a cube is **inactive** but **exactly `3`** of its neighbors are active, the cube becomes **active**. Otherwise, the cube remains **inactive**.

The engineers responsible for this experimental energy source would like you to simulate the pocket dimension and determine what the configuration of cubes should be at the end of the six-cycle boot process.

For example, consider the following initial state:


```
.#.
..#
###
```

Even though the pocket dimension is 3-dimensional, this initial state represents a small 2-dimensional slice of it. (In particular, this initial state defines a 3x3x1 region of the 3-dimensional space.)

Simulating a few cycles from this initial state produces the following configurations, where the result of each cycle is shown layer-by-layer at each given `z` coordinate (and the frame of view follows the active cells in each cycle):


```
Before any cycles:

z=0
.#.
..#
###


After 1 cycle:

z=-1
#..
..#
.#.

z=0
#.#
.##
.#.

z=1
#..
..#
.#.


After 2 cycles:

z=-2
.....
.....
..#..
.....
.....

z=-1
..#..
.#..#
....#
.#...
.....

z=0
##...
##...
#....
....#
.###.

z=1
..#..
.#..#
....#
.#...
.....

z=2
.....
.....
..#..
.....
.....


After 3 cycles:

z=-2
.......
.......
..##...
..###..
.......
.......
.......

z=-1
..#....
...#...
#......
.....##
.#...#.
..#.#..
...#...

z=0
...#...
.......
#......
.......
.....##
.##.#..
...#...

z=1
..#....
...#...
#......
.....##
.#...#.
..#.#..
...#...

z=2
.......
.......
..##...
..###..
.......
.......
.......
```

After the full six-cycle boot process completes, **`112`** cubes are left in the **active** state.

Starting with your given initial configuration, simulate six cycles. **How many cubes are left in the active state after the sixth cycle?**


> Your puzzle answer was `257`.


### Part Two

For some reason, your simulated results don't match what the experimental energy source engineers expected. Apparently, the pocket dimension actually has **four spatial dimensions**, not three.

The pocket dimension contains an infinite 4-dimensional grid. At every integer 4-dimensional coordinate (`x,y,z,w`), there exists a single cube (really, a **hypercube**) which is still either **active** or **inactive**.

Each cube only ever considers its **neighbors**: any of the 80 other cubes where any of their coordinates differ by at most `1`. For example, given the cube at `x=1,y=2,z=3,w=4`, its neighbors include the cube at `x=2,y=2,z=3,w=3`, the cube at `x=0,y=2,z=3,w=4`, and so on.

The initial state of the pocket dimension still consists of a small flat region of cubes. Furthermore, the same rules for cycle updating still apply: during each cycle, consider the **number of active neighbors** of each cube.

For example, consider the same initial state as in the example above. Even though the pocket dimension is 4-dimensional, this initial state represents a small 2-dimensional slice of it. (In particular, this initial state defines a 3x3x1x1 region of the 4-dimensional space.)

Simulating a few cycles from this initial state produces the following configurations, where the result of each cycle is shown layer-by-layer at each given `z` and `w` coordinate:


```
Before any cycles:

z=0, w=0
.#.
..#
###


After 1 cycle:

z=-1, w=-1
#..
..#
.#.

z=0, w=-1
#..
..#
.#.

z=1, w=-1
#..
..#
.#.

z=-1, w=0
#..
..#
.#.

z=0, w=0
#.#
.##
.#.

z=1, w=0
#..
..#
.#.

z=-1, w=1
#..
..#
.#.

z=0, w=1
#..
..#
.#.

z=1, w=1
#..
..#
.#.


After 2 cycles:

z=-2, w=-2
.....
.....
..#..
.....
.....

z=-1, w=-2
.....
.....
.....
.....
.....

z=0, w=-2
###..
##.##
#...#
.#..#
.###.

z=1, w=-2
.....
.....
.....
.....
.....

z=2, w=-2
.....
.....
..#..
.....
.....

z=-2, w=-1
.....
.....
.....
.....
.....

z=-1, w=-1
.....
.....
.....
.....
.....

z=0, w=-1
.....
.....
.....
.....
.....

z=1, w=-1
.....
.....
.....
.....
.....

z=2, w=-1
.....
.....
.....
.....
.....

z=-2, w=0
###..
##.##
#...#
.#..#
.###.

z=-1, w=0
.....
.....
.....
.....
.....

z=0, w=0
.....
.....
.....
.....
.....

z=1, w=0
.....
.....
.....
.....
.....

z=2, w=0
###..
##.##
#...#
.#..#
.###.

z=-2, w=1
.....
.....
.....
.....
.....

z=-1, w=1
.....
.....
.....
.....
.....

z=0, w=1
.....
.....
.....
.....
.....

z=1, w=1
.....
.....
.....
.....
.....

z=2, w=1
.....
.....
.....
.....
.....

z=-2, w=2
.....
.....
..#..
.....
.....

z=-1, w=2
.....
.....
.....
.....
.....

z=0, w=2
###..
##.##
#...#
.#..#
.###.

z=1, w=2
.....
.....
.....
.....
.....

z=2, w=2
.....
.....
..#..
.....
.....
```

After the full six-cycle boot process completes, **`848`** cubes are left in the **active** state.

Starting with your given initial configuration, simulate six cycles in a 4-dimensional space. **How many cubes are left in the active state after the sixth cycle?**


> Your puzzle answer was `2532`.

## [Day 18: Operation Order](https://adventofcode.com/2020/day/18)

As you look out the window and notice a heavily-forested continent slowly appear over the horizon, you are interrupted by the child sitting next to you. They're curious if you could help them with their math homework.

Unfortunately, it seems like this "math" [follows different rules](https://www.youtube.com/watch?v=3QtRK7Y2pPU&t=15) than you remember.

The homework (your puzzle input) consists of a series of expressions that consist of addition (`+`), multiplication (`*`), and parentheses (`(...)`). Just like normal math, parentheses indicate that the expression inside must be evaluated before it can be used by the surrounding expression. Addition still finds the sum of the numbers on both sides of the operator, and multiplication still finds the product.

However, the rules of **operator precedence** have changed. Rather than evaluating multiplication before addition, the operators have the **same precedence**, and are evaluated left-to-right regardless of the order in which they appear.

For example, the steps to evaluate the expression `1 + 2 * 3 + 4 * 5 + 6` are as follows:


```
1 + 2 * 3 + 4 * 5 + 6
  3   * 3 + 4 * 5 + 6
      9   + 4 * 5 + 6
         13   * 5 + 6
             65   + 6
                 71
```

Parentheses can override this order; for example, here is what happens if parentheses are added to form `1 + (2 * 3) + (4 * (5 + 6))`:


```
1 + (2 * 3) + (4 * (5 + 6))
1 +    6    + (4 * (5 + 6))
     7      + (4 * (5 + 6))
     7      + (4 *   11   )
     7      +     44
            51
```

Here are a few more examples:


+ `2 * 3 + (4 * 5)` becomes **`26`**.
+ `5 + (8 * 3 + 9 + 3 * 4 * 3)` becomes **`437`**.
+ `5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))` becomes **`12240`**.
+ `((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2` becomes **`13632`**.

Before you can help with the homework, you need to understand it yourself. **Evaluate the expression on each line of the homework; what is the sum of the resulting values?**


> Your puzzle answer was `45283905029161`.


### Part Two

You manage to answer the child's questions and they finish part 1 of their homework, but get stuck when they reach the next section: **advanced** math.

Now, addition and multiplication have **different** precedence levels, but they're not the ones you're familiar with. Instead, addition is evaluated **before** multiplication.

For example, the steps to evaluate the expression `1 + 2 * 3 + 4 * 5 + 6` are now as follows:


```
1 + 2 * 3 + 4 * 5 + 6
  3   * 3 + 4 * 5 + 6
  3   *   7   * 5 + 6
  3   *   7   *  11
     21       *  11
         231
```

Here are the other examples from above:


+ `1 + (2 * 3) + (4 * (5 + 6))` still becomes **`51`**.
+ `2 * 3 + (4 * 5)` becomes **`46`**.
+ `5 + (8 * 3 + 9 + 3 * 4 * 3)` becomes **`1445`**.
+ `5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))` becomes **`669060`**.
+ `((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2` becomes **`23340`**.

**What do you get if you add up the results of evaluating the homework problems using these new rules?**


> Your puzzle answer was `216975281211165`.

## [Day 19: Monster Messages](https://adventofcode.com/2020/day/19)

You land in an airport surrounded by dense forest. As you walk to your high-speed train, the Elves at the Mythical Information Bureau contact you again. They think their satellite has collected an image of a **sea monster**! Unfortunately, the connection to the satellite is having problems, and many of the messages sent back from the satellite have been corrupted.

They sent you a list of **the rules valid messages should obey** and a list of **received messages** they've collected so far (your puzzle input).

The **rules for valid messages** (the top part of your puzzle input) are numbered and build upon each other. For example:


```
0: 1 2
1: "a"
2: 1 3 | 3 1
3: "b"
```

Some rules, like `3: "b"`, simply match a single character (in this case, `b`).

The remaining rules list the sub-rules that must be followed; for example, the rule `0: 1 2` means that to match rule `0`, the text being checked must match rule `1`, and the text after the part that matched rule `1` must then match rule `2`.

Some of the rules have multiple lists of sub-rules separated by a pipe (`|`). This means that **at least one** list of sub-rules must match. (The ones that match might be different each time the rule is encountered.) For example, the rule `2: 1 3 | 3 1` means that to match rule `2`, the text being checked must match rule `1` followed by rule `3` **or** it must match rule `3` followed by rule `1`.

Fortunately, there are no loops in the rules, so the list of possible matches will be finite. Since rule `1` matches `a` and rule `3` matches `b`, rule `2` matches either `ab` or `ba`. Therefore, rule `0` matches `aab` or `aba`.

Here's a more interesting example:


```
0: 4 1 5
1: 2 3 | 3 2
2: 4 4 | 5 5
3: 4 5 | 5 4
4: "a"
5: "b"
```

Here, because rule `4` matches `a` and rule `5` matches `b`, rule `2` matches two letters that are the same (`aa` or `bb`), and rule `3` matches two letters that are different (`ab` or `ba`).

Since rule `1` matches rules `2` and `3` once each in either order, it must match two pairs of letters, one pair with matching letters and one pair with different letters. This leaves eight possibilities: `aaab`, `aaba`, `bbab`, `bbba`, `abaa`, `abbb`, `baaa`, or `babb`.

Rule `0`, therefore, matches `a` (rule `4`), then any of the eight options from rule `1`, then `b` (rule `5`): `aaaabb`, `aaabab`, `abbabb`, `abbbab`, `aabaab`, `aabbbb`, `abaaab`, or `ababbb`.

The **received messages** (the bottom part of your puzzle input) need to be checked against the rules so you can determine which are valid and which are corrupted. Including the rules and the messages together, this might look like:


```
0: 4 1 5
1: 2 3 | 3 2
2: 4 4 | 5 5
3: 4 5 | 5 4
4: "a"
5: "b"

ababbb
bababa
abbbab
aaabbb
aaaabbb
```

Your goal is to determine **the number of messages that completely match rule `0`**. In the above example, `ababbb` and `abbbab` match, but `bababa`, `aaabbb`, and `aaaabbb` do not, producing the answer **`2`**. The whole message must match all of rule `0`; there can't be extra unmatched characters in the message. (For example, `aaaabbb` might appear to match rule `0` above, but it has an extra unmatched `b` on the end.)

**How many messages completely match rule `0`?**


> Your puzzle answer was `132`.


### Part Two

As you look over the list of messages, you realize your matching rules aren't quite right. To fix them, completely replace rules `8: 42` and `11: 42 31` with the following:


```
8: 42 | 42 8
11: 42 31 | 42 11 31
```

This small change has a big impact: now, the rules **do** contain loops, and the list of messages they could hypothetically match is infinite. You'll need to determine how these changes affect which messages are valid.

Fortunately, many of the rules are unaffected by this change; it might help to start by looking at which rules always match the same set of values and how **those** rules (especially rules `42` and `31`) are used by the new versions of rules `8` and `11`.

(Remember, **you only need to handle the rules you have**; building a solution that could handle any hypothetical combination of rules would be [significantly more difficult](https://en.wikipedia.org/wiki/Formal_grammar).)

For example:


```
42: 9 14 | 10 1
9: 14 27 | 1 26
10: 23 14 | 28 1
1: "a"
11: 42 31
5: 1 14 | 15 1
19: 14 1 | 14 14
12: 24 14 | 19 1
16: 15 1 | 14 14
31: 14 17 | 1 13
6: 14 14 | 1 14
2: 1 24 | 14 4
0: 8 11
13: 14 3 | 1 12
15: 1 | 14
17: 14 2 | 1 7
23: 25 1 | 22 14
28: 16 1
4: 1 1
20: 14 14 | 1 15
3: 5 14 | 16 1
27: 1 6 | 14 18
14: "b"
21: 14 1 | 1 14
25: 1 1 | 1 14
22: 14 14
8: 42
26: 14 22 | 1 20
18: 15 15
7: 14 5 | 1 21
24: 14 1

abbbbbabbbaaaababbaabbbbabababbbabbbbbbabaaaa
bbabbbbaabaabba
babbbbaabbbbbabbbbbbaabaaabaaa
aaabbbbbbaaaabaababaabababbabaaabbababababaaa
bbbbbbbaaaabbbbaaabbabaaa
bbbababbbbaaaaaaaabbababaaababaabab
ababaaaaaabaaab
ababaaaaabbbaba
baabbaaaabbaaaababbaababb
abbbbabbbbaaaababbbbbbaaaababb
aaaaabbaabaaaaababaa
aaaabbaaaabbaaa
aaaabbaabbaaaaaaabbbabbbaaabbaabaaa
babaaabbbaaabaababbaabababaaab
aabbbbbaabbbaaaaaabbbbbababaaaaabbaaabba
```

Without updating rules `8` and `11`, these rules only match three messages: `bbabbbbaabaabba`, `ababaaaaaabaaab`, and `ababaaaaabbbaba`.

However, after updating rules `8` and `11`, a total of **`12`** messages match:


+ `bbabbbbaabaabba`
+ `babbbbaabbbbbabbbbbbaabaaabaaa`
+ `aaabbbbbbaaaabaababaabababbabaaabbababababaaa`
+ `bbbbbbbaaaabbbbaaabbabaaa`
+ `bbbababbbbaaaaaaaabbababaaababaabab`
+ `ababaaaaaabaaab`
+ `ababaaaaabbbaba`
+ `baabbaaaabbaaaababbaababb`
+ `abbbbabbbbaaaababbbbbbaaaababb`
+ `aaaaabbaabaaaaababaa`
+ `aaaabbaabbaaaaaaabbbabbbaaabbaabaaa`
+ `aabbbbbaabbbaaaaaabbbbbababaaaaabbaaabba`

**After updating rules `8` and `11`, how many messages completely match rule `0`?**


> Your puzzle answer was `306`.

## [Day 20: Jurassic Jigsaw](https://adventofcode.com/2020/day/20)

The high-speed train leaves the forest and quickly carries you south. You can even see a desert in the distance! Since you have some spare time, you might as well see if there was anything interesting in the image the Mythical Information Bureau satellite captured.

After decoding the satellite messages, you discover that the data actually contains many small images created by the satellite's **camera array**. The camera array consists of many cameras; rather than produce a single square image, they produce many smaller square image **tiles** that need to be **reassembled back into a single image**.

Each camera in the camera array returns a single monochrome **image tile** with a random unique **ID number**.  The tiles (your puzzle input) arrived in a random order.

Worse yet, the camera array appears to be malfunctioning: each image tile has been **rotated and flipped to a random orientation**. Your first task is to reassemble the original image by orienting the tiles so they fit together.

To show how the tiles should be reassembled, each tile's image data includes a border that should line up exactly with its adjacent tiles. All tiles have this border, and the border lines up exactly when the tiles are both oriented correctly. Tiles at the edge of the image also have this border, but the outermost edges won't line up with any other tiles.

For example, suppose you have the following nine tiles:


```
Tile 2311:
..##.#..#.
##..#.....
#...##..#.
####.#...#
##.##.###.
##...#.###
.#.#.#..##
..#....#..
###...#.#.
..###..###

Tile 1951:
#.##...##.
#.####...#
.....#..##
#...######
.##.#....#
.###.#####
###.##.##.
.###....#.
..#.#..#.#
#...##.#..

Tile 1171:
####...##.
#..##.#..#
##.#..#.#.
.###.####.
..###.####
.##....##.
.#...####.
#.##.####.
####..#...
.....##...

Tile 1427:
###.##.#..
.#..#.##..
.#.##.#..#
#.#.#.##.#
....#...##
...##..##.
...#.#####
.#.####.#.
..#..###.#
..##.#..#.

Tile 1489:
##.#.#....
..##...#..
.##..##...
..#...#...
#####...#.
#..#.#.#.#
...#.#.#..
##.#...##.
..##.##.##
###.##.#..

Tile 2473:
#....####.
#..#.##...
#.##..#...
######.#.#
.#...#.#.#
.#########
.###.#..#.
########.#
##...##.#.
..###.#.#.

Tile 2971:
..#.#....#
#...###...
#.#.###...
##.##..#..
.#####..##
.#..####.#
#..#.#..#.
..####.###
..#.#.###.
...#.#.#.#

Tile 2729:
...#.#.#.#
####.#....
..#.#.....
....#..#.#
.##..##.#.
.#.####...
####.#.#..
##.####...
##..#.##..
#.##...##.

Tile 3079:
#.#.#####.
.#..######
..#.......
######....
####.#..#.
.#...#.##.
#.#####.##
..#.###...
..#.......
..#.###...
```

By rotating, flipping, and rearranging them, you can find a square arrangement that causes all adjacent borders to line up:


```
#...##.#.. ..###..### #.#.#####.
..#.#..#.# ###...#.#. .#..######
.###....#. ..#....#.. ..#.......
###.##.##. .#.#.#..## ######....
.###.##### ##...#.### ####.#..#.
.##.#....# ##.##.###. .#...#.##.
#...###### ####.#...# #.#####.##
.....#..## #...##..#. ..#.###...
#.####...# ##..#..... ..#.......
#.##...##. ..##.#..#. ..#.###...

#.##...##. ..##.#..#. ..#.###...
##..#.##.. ..#..###.# ##.##....#
##.####... .#.####.#. ..#.###..#
####.#.#.. ...#.##### ###.#..###
.#.####... ...##..##. .######.##
.##..##.#. ....#...## #.#.#.#...
....#..#.# #.#.#.##.# #.###.###.
..#.#..... .#.##.#..# #.###.##..
####.#.... .#..#.##.. .######...
...#.#.#.# ###.##.#.. .##...####

...#.#.#.# ###.##.#.. .##...####
..#.#.###. ..##.##.## #..#.##..#
..####.### ##.#...##. .#.#..#.##
#..#.#..#. ...#.#.#.. .####.###.
.#..####.# #..#.#.#.# ####.###..
.#####..## #####...#. .##....##.
##.##..#.. ..#...#... .####...#.
#.#.###... .##..##... .####.##.#
#...###... ..##...#.. ...#..####
..#.#....# ##.#.#.... ...##.....
```

For reference, the IDs of the above tiles are:


```
1951    2311    3079
2729    1427    2473
2971    1489    1171
```

To check that you've assembled the image correctly, multiply the IDs of the four corner tiles together. If you do this with the assembled tiles from the example above, you get `1951 * 3079 * 2971 * 1171` = **`20899048083289`**.

Assemble the tiles into an image. **What do you get if you multiply together the IDs of the four corner tiles?**


> Your puzzle answer was `8581320593371`.


### Part Two

Now, you're ready to **check the image for sea monsters**.

The borders of each tile are not part of the actual image; start by removing them.

In the example above, the tiles become:


```
.#.#..#. ##...#.# #..#####
###....# .#....#. .#......
##.##.## #.#.#..# #####...
###.#### #...#.## ###.#..#
##.#.... #.##.### #...#.##
...##### ###.#... .#####.#
....#..# ...##..# .#.###..
.####... #..#.... .#......

#..#.##. .#..###. #.##....
#.####.. #.####.# .#.###..
###.#.#. ..#.#### ##.#..##
#.####.. ..##..## ######.#
##..##.# ...#...# .#.#.#..
...#..#. .#.#.##. .###.###
.#.#.... #.##.#.. .###.##.
###.#... #..#.##. ######..

.#.#.### .##.##.# ..#.##..
.####.## #.#...## #.#..#.#
..#.#..# ..#.#.#. ####.###
#..####. ..#.#.#. ###.###.
#####..# ####...# ##....##
#.##..#. .#...#.. ####...#
.#.###.. ##..##.. ####.##.
...###.. .##...#. ..#..###
```

Remove the gaps to form the actual image:


```
.#.#..#.##...#.##..#####
###....#.#....#..#......
##.##.###.#.#..######...
###.#####...#.#####.#..#
##.#....#.##.####...#.##
...########.#....#####.#
....#..#...##..#.#.###..
.####...#..#.....#......
#..#.##..#..###.#.##....
#.####..#.####.#.#.###..
###.#.#...#.######.#..##
#.####....##..########.#
##..##.#...#...#.#.#.#..
...#..#..#.#.##..###.###
.#.#....#.##.#...###.##.
###.#...#..#.##.######..
.#.#.###.##.##.#..#.##..
.####.###.#...###.#..#.#
..#.#..#..#.#.#.####.###
#..####...#.#.#.###.###.
#####..#####...###....##
#.##..#..#...#..####...#
.#.###..##..##..####.##.
...###...##...#...#..###
```

Now, you're ready to search for sea monsters! Because your image is monochrome, a sea monster will look like this:


```
                  #
#    ##    ##    ###
 #  #  #  #  #  #
```

When looking for this pattern in the image, **the spaces can be anything**; only the `#` need to match. Also, you might need to rotate or flip your image before it's oriented correctly to find sea monsters. In the above image, **after flipping and rotating it** to the appropriate orientation, there are **two** sea monsters (marked with `O`):


```
.####...#####..#...###..
#####..#..#.#.####..#.#.
.#.#...#.###...#.##.O#..
#.O.##.OO#.#.OO.##.OOO##
..#O.#O#.O##O..O.#O##.##
...#.#..##.##...#..#..##
#.##.#..#.#..#..##.#.#..
.###.##.....#...###.#...
#.####.#.#....##.#..#.#.
##...#..#....#..#...####
..#.##...###..#.#####..#
....#.##.#.#####....#...
..##.##.###.....#.##..#.
#...#...###..####....##.
.#.##...#.##.#.#.###...#
#.###.#..####...##..#...
#.###...#.##...#.##O###.
.O##.#OO.###OO##..OOO##.
..O#.O..O..O.#O##O##.###
#.#..##.########..#..##.
#.#####..#.#...##..#....
#....##..#.#########..##
#...#.....#..##...###.##
#..###....##.#...##.##.#
```

Determine how rough the waters are in the sea monsters' habitat by counting the number of `#` that are **not** part of a sea monster. In the above example, the habitat's water roughness is **`273`**.

**How many `#` are not part of a sea monster?**


> Your puzzle answer was `2031`.

## [Day 21: Allergen Assessment](https://adventofcode.com/2020/day/21)

You reach the train's last stop and the closest you can get to your vacation island without getting wet. There aren't even any boats here, but nothing can stop you now: you build a raft. You just need a few days' worth of food for your journey.

You don't speak the local language, so you can't read any ingredients lists. However, sometimes, allergens are listed in a language you **do** understand. You should be able to use this information to determine which ingredient contains which allergen and work out which foods are safe to take with you on your trip.

You start by compiling a list of foods (your puzzle input), one food per line. Each line includes that food's **ingredients list** followed by some or all of the allergens the food contains.

Each allergen is found in exactly one ingredient. Each ingredient contains zero or one allergen. **Allergens aren't always marked**; when they're listed (as in `(contains nuts, shellfish)` after an ingredients list), the ingredient that contains each listed allergen will be **somewhere in the corresponding ingredients list**. However, even if an allergen isn't listed, the ingredient that contains that allergen could still be present: maybe they forgot to label it, or maybe it was labeled in a language you don't know.

For example, consider the following list of foods:


```
mxmxvkd kfcds sqjhc nhms (contains dairy, fish)
trh fvjkl sbzzf mxmxvkd (contains dairy)
sqjhc fvjkl (contains soy)
sqjhc mxmxvkd sbzzf (contains fish)
```

The first food in the list has four ingredients (written in a language you don't understand): `mxmxvkd`, `kfcds`, `sqjhc`, and `nhms`. While the food might contain other allergens, a few allergens the food definitely contains are listed afterward: `dairy` and `fish`.

The first step is to determine which ingredients **can't possibly** contain any of the allergens in any food in your list. In the above example, none of the ingredients `kfcds`, `nhms`, `sbzzf`, or `trh` can contain an allergen. Counting the number of times any of these ingredients appear in any ingredients list produces **`5`**: they all appear once each except `sbzzf`, which appears twice.

Determine which ingredients cannot possibly contain any of the allergens in your list. **How many times do any of those ingredients appear?**


> Your puzzle answer was `2262`.


### Part Two

Now that you've isolated the inert ingredients, you should have enough information to figure out which ingredient contains which allergen.

In the above example:


+ `mxmxvkd` contains `dairy`.
+ `sqjhc` contains `fish`.
+ `fvjkl` contains `soy`.

Arrange the ingredients **alphabetically by their allergen** and separate them by commas to produce your **canonical dangerous ingredient list**. (There should **not be any spaces** in your canonical dangerous ingredient list.) In the above example, this would be **`mxmxvkd,sqjhc,fvjkl`**.

Time to stock your raft with supplies. **What is your canonical dangerous ingredient list?**


> Your puzzle answer was `cxsvdm,glf,rsbxb,xbnmzr,txdmlzd,vlblq,mtnh,mptbpz`.

## [Day 22: Crab Combat](https://adventofcode.com/2020/day/22)

It only takes a few hours of sailing the ocean on a raft for boredom to sink in. Fortunately, you brought a small deck of [space cards](https://adventofcode.com/2020/2019/day/22)! You'd like to play a game of **Combat**, and there's even an opponent available: a small crab that climbed aboard your raft before you left.

Fortunately, it doesn't take long to teach the crab the rules.

Before the game starts, split the cards so each player has their own deck (your puzzle input). Then, the game consists of a series of **rounds**: both players draw their top card, and the player with the higher-valued card wins the round. The winner keeps both cards, placing them on the bottom of their own deck so that the winner's card is above the other card. If this causes a player to have all of the cards, they win, and the game ends.

For example, consider the following starting decks:


```
Player 1:
9
2
6
3
1

Player 2:
5
8
4
7
10
```

This arrangement means that player 1's deck contains 5 cards, with `9` on top and `1` on the bottom; player 2's deck also contains 5 cards, with `5` on top and `10` on the bottom.

The first round begins with both players drawing the top card of their decks: `9` and `5`. Player 1 has the higher card, so both cards move to the bottom of player 1's deck such that `9` is above `5`. In total, it takes 29 rounds before a player has all of the cards:


```
-- Round 1 --
Player 1's deck: 9, 2, 6, 3, 1
Player 2's deck: 5, 8, 4, 7, 10
Player 1 plays: 9
Player 2 plays: 5
Player 1 wins the round!

-- Round 2 --
Player 1's deck: 2, 6, 3, 1, 9, 5
Player 2's deck: 8, 4, 7, 10
Player 1 plays: 2
Player 2 plays: 8
Player 2 wins the round!

-- Round 3 --
Player 1's deck: 6, 3, 1, 9, 5
Player 2's deck: 4, 7, 10, 8, 2
Player 1 plays: 6
Player 2 plays: 4
Player 1 wins the round!

-- Round 4 --
Player 1's deck: 3, 1, 9, 5, 6, 4
Player 2's deck: 7, 10, 8, 2
Player 1 plays: 3
Player 2 plays: 7
Player 2 wins the round!

-- Round 5 --
Player 1's deck: 1, 9, 5, 6, 4
Player 2's deck: 10, 8, 2, 7, 3
Player 1 plays: 1
Player 2 plays: 10
Player 2 wins the round!

...several more rounds pass...

-- Round 27 --
Player 1's deck: 5, 4, 1
Player 2's deck: 8, 9, 7, 3, 2, 10, 6
Player 1 plays: 5
Player 2 plays: 8
Player 2 wins the round!

-- Round 28 --
Player 1's deck: 4, 1
Player 2's deck: 9, 7, 3, 2, 10, 6, 8, 5
Player 1 plays: 4
Player 2 plays: 9
Player 2 wins the round!

-- Round 29 --
Player 1's deck: 1
Player 2's deck: 7, 3, 2, 10, 6, 8, 5, 9, 4
Player 1 plays: 1
Player 2 plays: 7
Player 2 wins the round!


== Post-game results ==
Player 1's deck:
Player 2's deck: 3, 2, 10, 6, 8, 5, 9, 4, 7, 1
```

Once the game ends, you can calculate the winning player's **score**. The bottom card in their deck is worth the value of the card multiplied by 1, the second-from-the-bottom card is worth the value of the card multiplied by 2, and so on. With 10 cards, the top card is worth the value on the card multiplied by 10. In this example, the winning player's score is:


```
   3 * 10
+  2 *  9
+ 10 *  8
+  6 *  7
+  8 *  6
+  5 *  5
+  9 *  4
+  4 *  3
+  7 *  2
+  1 *  1
= 306
```

So, once the game ends, the winning player's score is **`306`**.

Play the small crab in a game of Combat using the two decks you just dealt. **What is the winning player's score?**


> Your puzzle answer was `32472`.


### Part Two

You lost to the small crab! Fortunately, crabs aren't very good at recursion. To defend your honor as a Raft Captain, you challenge the small crab to a game of **Recursive Combat**.

Recursive Combat still starts by splitting the cards into two decks (you offer to play with the same starting decks as before - it's only fair). Then, the game consists of a series of **rounds** with a few changes:


+ Before either player deals a card, if there was a previous round in this game that had exactly the same cards in the same order in the same players' decks, the **game** instantly ends in a win for player 1. Previous rounds from other games are not considered. (This prevents infinite games of Recursive Combat, which everyone agrees is a bad idea.)
+ Otherwise, this round's cards must be in a new configuration; the players begin the round by each drawing the top card of their deck as normal.
+ If both players have at least as many cards remaining in their deck as the value of the card they just drew, the winner of the round is determined by playing a new game of Recursive Combat (see below).
+ Otherwise, at least one player must not have enough cards left in their deck to recurse; the winner of the round is the player with the higher-value card.

As in regular Combat, the winner of the round (even if they won the round by winning a sub-game) takes the two cards dealt at the beginning of the round and places them on the bottom of their own deck (again so that the winner's card is above the other card). Note that the winner's card might be **the lower-valued of the two cards** if they won the round due to winning a sub-game. If collecting cards by winning the round causes a player to have all of the cards, they win, and the game ends.

Here is an example of a small game that would loop forever without the infinite game prevention rule:


```
Player 1:
43
19

Player 2:
2
29
14
```

During a round of Recursive Combat, if both players have at least as many cards in their own decks as the number on the card they just dealt, the winner of the round is determined by recursing into a sub-game of Recursive Combat. (For example, if player 1 draws the `3` card, and player 2 draws the `7` card, this would occur if player 1 has at least 3 cards left and player 2 has at least 7 cards left, not counting the `3` and `7` cards that were drawn.)

To play a sub-game of Recursive Combat, each player creates a new deck by making a **copy** of the next cards in their deck (the quantity of cards copied is equal to the number on the card they drew to trigger the sub-game). During this sub-game, the game that triggered it is on hold and completely unaffected; no cards are removed from players' decks to form the sub-game. (For example, if player 1 drew the `3` card, their deck in the sub-game would be **copies** of the next three cards in their deck.)

Here is a complete example of gameplay, where `Game 1` is the primary game of Recursive Combat:


```
=== Game 1 ===

-- Round 1 (Game 1) --
Player 1's deck: 9, 2, 6, 3, 1
Player 2's deck: 5, 8, 4, 7, 10
Player 1 plays: 9
Player 2 plays: 5
Player 1 wins round 1 of game 1!

-- Round 2 (Game 1) --
Player 1's deck: 2, 6, 3, 1, 9, 5
Player 2's deck: 8, 4, 7, 10
Player 1 plays: 2
Player 2 plays: 8
Player 2 wins round 2 of game 1!

-- Round 3 (Game 1) --
Player 1's deck: 6, 3, 1, 9, 5
Player 2's deck: 4, 7, 10, 8, 2
Player 1 plays: 6
Player 2 plays: 4
Player 1 wins round 3 of game 1!

-- Round 4 (Game 1) --
Player 1's deck: 3, 1, 9, 5, 6, 4
Player 2's deck: 7, 10, 8, 2
Player 1 plays: 3
Player 2 plays: 7
Player 2 wins round 4 of game 1!

-- Round 5 (Game 1) --
Player 1's deck: 1, 9, 5, 6, 4
Player 2's deck: 10, 8, 2, 7, 3
Player 1 plays: 1
Player 2 plays: 10
Player 2 wins round 5 of game 1!

-- Round 6 (Game 1) --
Player 1's deck: 9, 5, 6, 4
Player 2's deck: 8, 2, 7, 3, 10, 1
Player 1 plays: 9
Player 2 plays: 8
Player 1 wins round 6 of game 1!

-- Round 7 (Game 1) --
Player 1's deck: 5, 6, 4, 9, 8
Player 2's deck: 2, 7, 3, 10, 1
Player 1 plays: 5
Player 2 plays: 2
Player 1 wins round 7 of game 1!

-- Round 8 (Game 1) --
Player 1's deck: 6, 4, 9, 8, 5, 2
Player 2's deck: 7, 3, 10, 1
Player 1 plays: 6
Player 2 plays: 7
Player 2 wins round 8 of game 1!

-- Round 9 (Game 1) --
Player 1's deck: 4, 9, 8, 5, 2
Player 2's deck: 3, 10, 1, 7, 6
Player 1 plays: 4
Player 2 plays: 3
Playing a sub-game to determine the winner...

=== Game 2 ===

-- Round 1 (Game 2) --
Player 1's deck: 9, 8, 5, 2
Player 2's deck: 10, 1, 7
Player 1 plays: 9
Player 2 plays: 10
Player 2 wins round 1 of game 2!

-- Round 2 (Game 2) --
Player 1's deck: 8, 5, 2
Player 2's deck: 1, 7, 10, 9
Player 1 plays: 8
Player 2 plays: 1
Player 1 wins round 2 of game 2!

-- Round 3 (Game 2) --
Player 1's deck: 5, 2, 8, 1
Player 2's deck: 7, 10, 9
Player 1 plays: 5
Player 2 plays: 7
Player 2 wins round 3 of game 2!

-- Round 4 (Game 2) --
Player 1's deck: 2, 8, 1
Player 2's deck: 10, 9, 7, 5
Player 1 plays: 2
Player 2 plays: 10
Player 2 wins round 4 of game 2!

-- Round 5 (Game 2) --
Player 1's deck: 8, 1
Player 2's deck: 9, 7, 5, 10, 2
Player 1 plays: 8
Player 2 plays: 9
Player 2 wins round 5 of game 2!

-- Round 6 (Game 2) --
Player 1's deck: 1
Player 2's deck: 7, 5, 10, 2, 9, 8
Player 1 plays: 1
Player 2 plays: 7
Player 2 wins round 6 of game 2!
The winner of game 2 is player 2!

...anyway, back to game 1.
Player 2 wins round 9 of game 1!

-- Round 10 (Game 1) --
Player 1's deck: 9, 8, 5, 2
Player 2's deck: 10, 1, 7, 6, 3, 4
Player 1 plays: 9
Player 2 plays: 10
Player 2 wins round 10 of game 1!

-- Round 11 (Game 1) --
Player 1's deck: 8, 5, 2
Player 2's deck: 1, 7, 6, 3, 4, 10, 9
Player 1 plays: 8
Player 2 plays: 1
Player 1 wins round 11 of game 1!

-- Round 12 (Game 1) --
Player 1's deck: 5, 2, 8, 1
Player 2's deck: 7, 6, 3, 4, 10, 9
Player 1 plays: 5
Player 2 plays: 7
Player 2 wins round 12 of game 1!

-- Round 13 (Game 1) --
Player 1's deck: 2, 8, 1
Player 2's deck: 6, 3, 4, 10, 9, 7, 5
Player 1 plays: 2
Player 2 plays: 6
Playing a sub-game to determine the winner...

=== Game 3 ===

-- Round 1 (Game 3) --
Player 1's deck: 8, 1
Player 2's deck: 3, 4, 10, 9, 7, 5
Player 1 plays: 8
Player 2 plays: 3
Player 1 wins round 1 of game 3!

-- Round 2 (Game 3) --
Player 1's deck: 1, 8, 3
Player 2's deck: 4, 10, 9, 7, 5
Player 1 plays: 1
Player 2 plays: 4
Playing a sub-game to determine the winner...

=== Game 4 ===

-- Round 1 (Game 4) --
Player 1's deck: 8
Player 2's deck: 10, 9, 7, 5
Player 1 plays: 8
Player 2 plays: 10
Player 2 wins round 1 of game 4!
The winner of game 4 is player 2!

...anyway, back to game 3.
Player 2 wins round 2 of game 3!

-- Round 3 (Game 3) --
Player 1's deck: 8, 3
Player 2's deck: 10, 9, 7, 5, 4, 1
Player 1 plays: 8
Player 2 plays: 10
Player 2 wins round 3 of game 3!

-- Round 4 (Game 3) --
Player 1's deck: 3
Player 2's deck: 9, 7, 5, 4, 1, 10, 8
Player 1 plays: 3
Player 2 plays: 9
Player 2 wins round 4 of game 3!
The winner of game 3 is player 2!

...anyway, back to game 1.
Player 2 wins round 13 of game 1!

-- Round 14 (Game 1) --
Player 1's deck: 8, 1
Player 2's deck: 3, 4, 10, 9, 7, 5, 6, 2
Player 1 plays: 8
Player 2 plays: 3
Player 1 wins round 14 of game 1!

-- Round 15 (Game 1) --
Player 1's deck: 1, 8, 3
Player 2's deck: 4, 10, 9, 7, 5, 6, 2
Player 1 plays: 1
Player 2 plays: 4
Playing a sub-game to determine the winner...

=== Game 5 ===

-- Round 1 (Game 5) --
Player 1's deck: 8
Player 2's deck: 10, 9, 7, 5
Player 1 plays: 8
Player 2 plays: 10
Player 2 wins round 1 of game 5!
The winner of game 5 is player 2!

...anyway, back to game 1.
Player 2 wins round 15 of game 1!

-- Round 16 (Game 1) --
Player 1's deck: 8, 3
Player 2's deck: 10, 9, 7, 5, 6, 2, 4, 1
Player 1 plays: 8
Player 2 plays: 10
Player 2 wins round 16 of game 1!

-- Round 17 (Game 1) --
Player 1's deck: 3
Player 2's deck: 9, 7, 5, 6, 2, 4, 1, 10, 8
Player 1 plays: 3
Player 2 plays: 9
Player 2 wins round 17 of game 1!
The winner of game 1 is player 2!


== Post-game results ==
Player 1's deck:
Player 2's deck: 7, 5, 6, 2, 4, 1, 10, 8, 9, 3
```

After the game, the winning player's score is calculated from the cards they have in their original deck using the same rules as regular Combat. In the above game, the winning player's score is **`291`**.

Defend your honor as Raft Captain by playing the small crab in a game of Recursive Combat using the same two decks as before. **What is the winning player's score?**


> Your puzzle answer was `36463`.

## [Day 23: Crab Cups](https://adventofcode.com/2020/day/23)

The small crab challenges **you** to a game! The crab is going to mix up some cups, and you have to predict where they'll end up.

The cups will be arranged in a circle and labeled **clockwise** (your puzzle input). For example, if your labeling were `32415`, there would be five cups in the circle; going clockwise around the circle from the first cup, the cups would be labeled `3`, `2`, `4`, `1`, `5`, and then back to `3` again.

Before the crab starts, it will designate the first cup in your list as the **current cup**. The crab is then going to do **100 moves**.

Each **move**, the crab does the following actions:


+ The crab picks up the **three cups** that are immediately **clockwise** of the **current cup**. They are removed from the circle; cup spacing is adjusted as necessary to maintain the circle.
+ The crab selects a **destination cup**: the cup with a **label** equal to the **current cup's** label minus one. If this would select one of the cups that was just picked up, the crab will keep subtracting one until it finds a cup that wasn't just picked up. If at any point in this process the value goes below the lowest value on any cup's label, it **wraps around** to the highest value on any cup's label instead.
+ The crab places the cups it just picked up so that they are **immediately clockwise** of the destination cup. They keep the same order as when they were picked up.
+ The crab selects a new **current cup**: the cup which is immediately clockwise of the current cup.

For example, suppose your cup labeling were `389125467`. If the crab were to do merely 10 moves, the following changes would occur:


```
-- move 1 --
cups: (3) 8  9  1  2  5  4  6  7 
pick up: 8, 9, 1
destination: 2

-- move 2 --
cups:  3 (2) 8  9  1  5  4  6  7 
pick up: 8, 9, 1
destination: 7

-- move 3 --
cups:  3  2 (5) 4  6  7  8  9  1 
pick up: 4, 6, 7
destination: 3

-- move 4 --
cups:  7  2  5 (8) 9  1  3  4  6 
pick up: 9, 1, 3
destination: 7

-- move 5 --
cups:  3  2  5  8 (4) 6  7  9  1 
pick up: 6, 7, 9
destination: 3

-- move 6 --
cups:  9  2  5  8  4 (1) 3  6  7 
pick up: 3, 6, 7
destination: 9

-- move 7 --
cups:  7  2  5  8  4  1 (9) 3  6 
pick up: 3, 6, 7
destination: 8

-- move 8 --
cups:  8  3  6  7  4  1  9 (2) 5 
pick up: 5, 8, 3
destination: 1

-- move 9 --
cups:  7  4  1  5  8  3  9  2 (6)
pick up: 7, 4, 1
destination: 5

-- move 10 --
cups: (5) 7  4  1  8  3  9  2  6 
pick up: 7, 4, 1
destination: 3

-- final --
cups:  5 (8) 3  7  4  1  9  2  6 
```

In the above example, the cups' values are the labels as they appear moving clockwise around the circle; the **current cup** is marked with `( )`.

After the crab is done, what order will the cups be in? Starting **after the cup labeled `1`**, collect the other cups' labels clockwise into a single string with no extra characters; each number except `1` should appear exactly once. In the above example, after 10 moves, the cups clockwise from `1` are labeled `9`, `2`, `6`, `5`, and so on, producing **`92658374`**. If the crab were to complete all 100 moves, the order after cup `1` would be **`67384529`**.

Using your labeling, simulate 100 moves. **What are the labels on the cups after cup `1`?**


> Your puzzle answer was `98752463`.


### Part Two

Due to what you can only assume is a mistranslation (you're not exactly fluent in Crab), you are quite surprised when the crab starts arranging **many** cups in a circle on your raft - **one million** (`1000000`) in total.

Your labeling is still correct for the first few cups; after that, the remaining cups are just numbered in an increasing fashion starting from the number after the highest number in your list and proceeding one by one until one million is reached. (For example, if your labeling were `54321`, the cups would be numbered `5`, `4`, `3`, `2`, `1`, and then start counting up from `6` until one million is reached.) In this way, every number from one through one million is used exactly once.

After discovering where you made the mistake in translating Crab Numbers, you realize the small crab isn't going to do merely 100 moves; the crab is going to do **ten million** (`10000000`) moves!

The crab is going to hide your **stars** - one each - under the **two cups that will end up immediately clockwise of cup `1`**. You can have them if you predict what the labels on those cups will be when the crab is finished.

In the above example (`389125467`), this would be `934001` and then `159792`; multiplying these together produces **`149245887792`**.

Determine which two cups will end up immediately clockwise of cup `1`. **What do you get if you multiply their labels together?**


> Your puzzle answer was `2000455861`.
