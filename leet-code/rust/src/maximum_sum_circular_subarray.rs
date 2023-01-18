use std::cmp::max;

pub fn max_subarray_sum_circular(nums: Vec<i32>) -> i32 {
    let suffix_maxs = nums
        .iter()
        .rev()
        .fold((0, vec![0]), |(mut sum, mut v), x| {
            sum += x;
            v.push(max(*v.last().unwrap(), sum));
            (sum, v)
        })
        .1;

    let mut max_sum = nums[0];
    let mut special_sum = nums[0];
    let mut current_max = 0;
    let mut suffix_sum = 0;

    let n = nums.len();
    nums.into_iter().enumerate().for_each(|(i, num)| {
        current_max = max(current_max, 0) + num;
        max_sum = max(max_sum, current_max);
        suffix_sum += num;

        if i + 1 < n {
            special_sum = max(special_sum, suffix_sum + suffix_maxs[n - i - 1]);
        }
    });

    max(max_sum, special_sum)
}
