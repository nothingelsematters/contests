# Leet Code

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
