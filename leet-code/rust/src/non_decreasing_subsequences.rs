use std::collections::HashSet;

pub fn find_subsequences(nums: Vec<i32>) -> Vec<Vec<i32>> {
    let mut result = HashSet::new();
    (0..nums.len()).for_each(|i| dfs(&nums, i, Vec::new(), &mut result));
    result.into_iter().collect()
}

fn dfs(nums: &[i32], index: usize, mut current: Vec<i32>, result: &mut HashSet<Vec<i32>>) {
    current.push(nums[index]);
    if current.len() >= 2 {
        result.insert(current.clone());
    }

    (index + 1..nums.len())
        .filter(|i| nums[index] <= nums[*i])
        .for_each(|i| dfs(nums, i, current.clone(), result))
}

#[cfg(test)]
mod tests {
    use std::collections::HashSet;

    #[test]
    fn sample() {
        let nums = vec![4, 6, 7, 7];
        let expected = vec![
            vec![4, 6],
            vec![4, 6, 7],
            vec![4, 6, 7, 7],
            vec![4, 7],
            vec![4, 7, 7],
            vec![6, 7],
            vec![6, 7, 7],
            vec![7, 7],
        ];
        assert_eq!(
            super::find_subsequences(nums)
                .into_iter()
                .collect::<HashSet<_>>(),
            expected.into_iter().collect::<HashSet<_>>(),
        )
    }
}
