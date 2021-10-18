# Leet Code

- [1. Two Sum](#1-two-sum)
- [2. Add Two Numbers](#2-add-two-numbers)
- [3. Longest Substring Without Repeating Characters](#3-longest-substring-without-repeating-characters)
- [9. Palindrome Number](#9-palindrome-number)

## 1. [Two Sum](https://leetcode.com/problems/two-sum/)

> Level: Easy
>
> [Solution](src/two_sum.rs)
>
> `Runtime: 0 ms, faster than 100.00% of Rust online submissions for Two Sum.`
>
> `Memory Usage: 2.6 MB, less than 10.33% of Rust online submissions for Two Sum.`

Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

### Example 1

```
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Output: Because nums[0] + nums[1] == 9, we return [0, 1].
```

### Example 2

```
Input: nums = [3,2,4], target = 6
Output: [1,2]
```

### Example 3

```
Input: nums = [3,3], target = 6
Output: [0,1]
```

### Constraints

- `2 <= nums.length <= 104`
- `-109 <= nums[i] <= 109`
- `-109 <= target <= 109`
- **Only one valid answer exists.**

## 2. [Add Two Numbers](https://leetcode.com/problems/add-two-numbers/)

> Medium
>
> [Solution](src/add_two_numbers.rs)
>
> `Runtime: 4 ms, faster than 81.56% of Rust online submissions for Add Two Numbers.`
>
> `Memory Usage: 2.1 MB, less than 88.44% of Rust online submissions for Add Two Numbers.`

You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

## Example 1

```
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.
```

## Example 2

```
Input: l1 = [0], l2 = [0]
Output: [0]
```

## Example 3

```
Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]
```

## Constraints

- The number of nodes in each linked list is in the range `[1, 100]`.
- `0 <= Node.val <= 9`
- It is guaranteed that the list represents a number that does not have leading zeros.

## 3. [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)

> Medium
>
> [Solution](src/longest_substring_without_repeating_characters.rs)
>
> `Runtime: 6 ms, faster than 53.38% of Rust online submissions for Longest Substring Without Repeating Characters.`
>
> `Memory Usage: 2.1 MB, less than 62.80% of Rust online submissions for Longest Substring Without Repeating Characters.`

Given a string `s`, find the length of the longest substring without repeating characters.

## Example 1

```
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
```

## Example 2

```
Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
```

## Example 3

```
Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
```

## Example 4

```
Input: s = ""
Output: 0
```

## Constraints

- `0 <= s.length <= 5 * 104`
- `s` consists of English letters, digits, symbols and spaces.

## 9. [Palindrome Number](https://leetcode.com/problems/palindrome-number/)

> Easy
>
> [Solution](src/palindrome_number.rs)
>
> `Runtime: 4 ms, faster than 90.47% of Rust online submissions for Palindrome Number.`
>
> `Memory Usage: 2 MB, less than 91.67% of Rust online submissions for Palindrome Number.`

Given an integer `x`, return `true` if `x` is palindrome integer.

An integer is a **palindrome** when it reads the same backward as forward. For example, `121` is palindrome while `123` is not.

## Example 1

```
Input: x = 121
Output: true
```

## Example 2

```
Input: x = -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
```

## Example 3

```
Input: x = 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
```

## Example 4

```
Input: x = -101
Output: false
```

## Constraints

`-231 <= x <= 231 - 1`


**Follow up**: Could you solve it without converting the integer to a string?
