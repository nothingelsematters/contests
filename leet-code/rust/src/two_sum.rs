use std::collections::HashMap;

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

#[cfg(test)]
mod test {
    #[test]
    fn sample() {
        let nums = vec![2, 7, 11, 15];
        let target = 9;

        assert_eq!(super::two_sum(nums, target), [0, 1]);
    }
}
