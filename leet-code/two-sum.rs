use std::collections::HashMap;

impl Solution {
    pub fn two_sum(nums: Vec<i32>, target: i32) -> Vec<i32> {
        let mut remains = HashMap::new();

        for (i, value) in nums.into_iter().enumerate() {
            if let Some(&j) = remains.get(&value) {
                return vec![j as i32, i as i32];
            }

            remains.insert(target - value, i);
        }

        panic!();
    }
}
